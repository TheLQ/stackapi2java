/**
 * Copyright (C) 2013 Leon Blakey <lord.quackstar at gmail.com>
 *
 * This file is part of stackapi2java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thelq.stackexchange.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.Module.SetupContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.sun.org.apache.bcel.internal.classfile.ClassParser;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.LocalVariable;
import java.io.IOException;
import java.io.InputStream;
import com.sun.org.apache.bcel.internal.classfile.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.ResponseEntry;
import org.thelq.stackexchange.api.queries.BaseQuery;
import org.thelq.stackexchange.api.queries.PagableQuery;
import org.thelq.stackexchange.api.queries.site.SiteQueries;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Slf4j
public class StackClient {
	protected static BitSet URI_SAFECHARS = new BitSet(256) {
		{
			BitSet unreserved = new BitSet(256);
			for (int i = 'a'; i <= 'z'; i++)
				unreserved.set(i);
			for (int i = 'A'; i <= 'Z'; i++)
				unreserved.set(i);
			for (int i = '0'; i <= '9'; i++)
				unreserved.set(i);
			unreserved.set('_');
			unreserved.set('-');
			unreserved.set('.');
			unreserved.set('*');
			or(unreserved);
		}
	};
	@Getter
	protected final String seApiKey;
	protected final ObjectMapper jsonMapper;
	@Getter
	@Setter
	protected String accessToken;

	public StackClient(String seApiKey) {
		Preconditions.checkNotNull(seApiKey);
		this.seApiKey = seApiKey;
		this.jsonMapper = new ObjectMapper();
		jsonMapper.registerModule(new JodaModule());
		jsonMapper.registerModule(new GuavaModule());
		jsonMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		jsonMapper.registerModule(new SimpleModule() {
			@Override
			@SuppressWarnings("unchecked")
			public void setupModule(SetupContext context) {
				super.setupModule(context);
				context.addDeserializers(new Deserializers.Base() {
					@Override
					public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
						return new UppercaseEnumDeserializer((Class<Enum<?>>) type);
					}
				});
			}
		});
	}

	protected URI createUri(@NonNull BaseQuery<?, ?> query) {
		//Run query verification
		Map<String, String> finalParameters = query.buildFinalParameters();
		if (query.isAuthRequired() && StringUtils.isBlank(accessToken))
			throw new RuntimeException("Query " + query.getClass().getName() + " requires an accessToken");
		String method = query.getMethod().getFinal();
		if (method.contains("{}"))
			throw new RuntimeException("Unreplaced vector remaining in method " + method);

		//Build a URI manually
		StringBuilder uriBuilder = new StringBuilder("https://api.stackexchange.com/2.1/")
				.append(method).append("?");
		if (StringUtils.isNotBlank(seApiKey))
			uriBuilder.append("key=").append(seApiKey).append("&");
		for (Map.Entry<String, String> curParam : finalParameters.entrySet()) {
			if (curParam.getKey() == null || curParam.getValue() == null)
				throw new NullPointerException("Parameters cannot be null: " + curParam.getKey() + "=" + curParam.getValue());
			uriBuilder.append(curParam.getKey()).append("=");

			//Encode value
			final ByteBuffer bb = Charsets.UTF_8.encode(curParam.getValue());
			while (bb.hasRemaining()) {
				final int b = bb.get() & 0xff;
				if (URI_SAFECHARS.get(b))
					uriBuilder.append((char) b);
				else if (b == ' ')
					uriBuilder.append('+');
				else {
					uriBuilder.append("%");
					final char hex1 = Character.toUpperCase(Character.forDigit((b >> 4) & 0xF, 16));
					final char hex2 = Character.toUpperCase(Character.forDigit(b & 0xF, 16));
					uriBuilder.append(hex1);
					uriBuilder.append(hex2);
				}
			}
			uriBuilder.append("&");
		}
		char lastChar = uriBuilder.charAt(uriBuilder.length() - 1);
		if (lastChar == '&' || lastChar == '?')
			uriBuilder.deleteCharAt(uriBuilder.length() - 1);

		return URI.create(uriBuilder.toString());
	}

	protected InputStream createResponse(URI uri) {
		try {
			HttpURLConnection  connection = (HttpURLConnection)uri.toURL().openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream connectionInput = (connection.getResponseCode() >= 400) ? connection.getErrorStream() : connection.getInputStream();
			if (connection.getContentEncoding().equalsIgnoreCase("gzip"))
				return new GZIPInputStream(connectionInput);
			else if (connection.getContentEncoding().equalsIgnoreCase("deflate"))
				return new DeflaterInputStream(connectionInput);
			else
				return connectionInput;
		} catch (Exception ex) {
			throw new RuntimeException("Cannot create response", ex);
		}
	}

	public <E extends ItemEntry> QueryIterable<E> queryIterable(@NonNull PagableQuery<?, E> query) {
		return new QueryIterable<E>(query, null);
	}
	
	public <E extends ItemEntry> QueryIterable<E> queryIterable(@NonNull PagableQuery<?, E> query, int maxPages) {
		return new QueryIterable<E>(query, maxPages);
	}

	public <E extends ItemEntry> ResponseEntry<E> query(@NonNull BaseQuery<?, E> query) {
		URI uri = createUri(query);
		try {
			//Do the request
			//TODO: Figure out how to handle errors with different status codes (causes Exception)
			//TODO: Handle backoff times
			log.debug("Querying API with URL: " + uri);

			//Handle errors
			//TODO: More efficent way to do this?
			JsonNode responseTree = jsonMapper.readTree(createResponse(uri));
			JsonNode errorIdNode = responseTree.get("error_id");
			if (errorIdNode != null)
				//Have an error, throw an exception
				throw new QueryErrorException(uri,
						errorIdNode.asInt(),
						responseTree.get("error_name").asText(),
						responseTree.get("error_message").asText());

			//No errors, convert to ResponseEntry
			//jsonMapper.writeTree(jsonMapper.getFactory().createGenerator(System.out).useDefaultPrettyPrinter(), responseTree);
			return jsonMapper.convertValue(responseTree, jsonMapper.getTypeFactory().constructParametricType(ResponseEntry.class, query.<E>getItemClass()));
		} catch (QueryErrorException e) {
			//No need to wrap
			throw e;
		} catch (Exception e) {
			throw new QueryException(uri, "Unable to excute query", e);
		}
	}

	public static void main(String[] args) throws IOException {
		try {			
			ClassParser parser = new ClassParser("target/classes/org/thelq/stackexchange/api/StackClient.class");
			JavaClass clazz = parser.parse();

			for (Method m : clazz.getMethods()) {
				System.out.println("Method: " + m.getName());
				int size = m.getArgumentTypes().length;
				if (!m.isStatic())
					size++;

				for (int i = 0; i < size; i++) {
					LocalVariable variable = m.getLocalVariableTable().getLocalVariable(i);
					System.out.println("  - Param: " + variable.getName());
				}
			}
			if (true)
				return;
			//Load up api key
			Properties authProperties = new Properties();
			authProperties.load(StackClient.class.getResourceAsStream("/auth.properties"));
			StackClient client = new StackClient(authProperties.getProperty("seApiKey"));

			//Get posts
			int counter = 0;
			for (ResponseEntry<AnswerEntry> curRespone : SiteQueries.DEFAULT.answersAll()
					.setSite("stackoverflow")
					.setFilter("!*2-Ks9DZr4MCSs67uH2q9UHUyUSATRXZkecYeRbMs")
					.queryIterable(client)) {
				log.info("Got " + curRespone.getItems().size() + " items on " + counter++);
				if (counter == 5)
					break;
			}

		} catch (QueryException e) {
			e.printStackTrace();
		}
	}

	@Getter
	public class QueryIterable<I extends ItemEntry> implements Iterable<ResponseEntry<I>> {
		protected final PagableQuery<?, I> firstQuery;
		protected final Integer maxPages; 

		public QueryIterable(PagableQuery<?, I> firstQuery, Integer maxPages) {
			Preconditions.checkArgument(firstQuery instanceof BaseQuery, "Query must be a BaseQuery");
			this.firstQuery = firstQuery;
			this.maxPages = maxPages;
		}

		public QueryIterator<I> iterator() {
			return new QueryIterator<I>(firstQuery, maxPages);
		}
	}

	@Getter
	public class QueryIterator<I extends ItemEntry> extends UnmodifiableIterator<ResponseEntry<I>> {
		protected final PagableQuery<?, I> query;
		protected final Integer maxPages;
		protected int curPage = -1;
		protected ResponseEntry<I> curResponse;

		public QueryIterator(PagableQuery<?, I> query, Integer maxPages) {
			this.query = query;
			if(maxPages != null)
				Preconditions.checkArgument(maxPages > 0, "Maximum pages must be greater than 0");
			this.maxPages = maxPages;
			if (query.getPage() != null) {
				Preconditions.checkArgument(query.getPage() > 0, "Page must be 1 or greater");
				curPage = query.getPage();
			} else
				curPage = 1;
		}

		public boolean hasNext() {
			return getResponse().hasMore();
		}

		public ResponseEntry<I> next() {
			ResponseEntry<I> response = getResponse();
			curResponse = null;
			return response;
		}

		protected ResponseEntry<I> getResponse() {
			if (curResponse == null) {
				if(curPage == maxPages)
					throw new NoSuchElementException("Already queried maximum number of pages:" + maxPages);
				query.setPage(curPage++);
				curResponse = ((BaseQuery<?, I>) query).query(StackClient.this);
			}
			return curResponse;
		}
	}

	protected static class UppercaseEnumDeserializer extends StdScalarDeserializer<Enum<?>> {
		protected UppercaseEnumDeserializer(Class<Enum<?>> clazz) {
			super(clazz);
		}

		@Override
		public Enum<?> deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			String text = jp.getText().toUpperCase();
			try {
				for (Enum<?> curEnum : (Enum[]) getValueClass().getEnumConstants())
					if (curEnum.name().equals(text))
						return curEnum;
				throw new RuntimeException("Could not find " + text + " in " + getValueClass().getName());
			} catch (Exception e) {
				throw new RuntimeException("Cannot deserialize enum " + getValueClass().getName() + " from " + text, e);
			}
		}
	}
}
