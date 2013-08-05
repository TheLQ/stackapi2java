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
import com.fasterxml.jackson.core.Version;
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
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.ResponseEntry;
import org.thelq.stackexchange.api.queries.AbstractQuery;
import org.thelq.stackexchange.api.queries.site.AnswerQueries;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Slf4j
public class StackClient {
	@Getter
	protected final String seApiKey;
	protected final HttpClient httpclient;
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
						return new LowerEnumDeserializer((Class<Enum<?>>) type);
					}
				});
			}
		});

		//Setup httpclient
		this.httpclient = HttpClientBuilder.create()
				.build();
	}

	protected URI createUri(@NonNull AbstractQuery<?, ?> query) {
		//Run query verification
		Map<String, String> finalParameters = query.buildFinalParameters();
		if (query.isAuthRequired() && StringUtils.isBlank(accessToken))
			throw new RuntimeException("Query " + query.getClass().getName() + " requires an accessToken");
		String method = query.getMethod().getFinal();
		if (method.contains("{}"))
			throw new RuntimeException("Unreplaced vector remaining in method " + method);

		//Build
		URIBuilder uriBuilder = new URIBuilder()
				.setScheme("https")
				.setHost("api.stackexchange.com")
				.setPath("/2.1/" + method);
		if (StringUtils.isNotBlank(seApiKey))
			uriBuilder.setParameter("key", seApiKey);
		for (Map.Entry<String, String> curParam : finalParameters.entrySet()) {
			if (curParam.getKey() == null || curParam.getValue() == null)
				throw new NullPointerException("Parameters cannot be null: " + curParam.getKey() + "=" + curParam.getValue());
			uriBuilder.setParameter(curParam.getKey(), curParam.getValue());
		}

		try {
			return uriBuilder.build();
		} catch (URISyntaxException ex) {
			throw new RuntimeException("Cannot build URL");
		}
	}

	public <E extends ItemEntry> ResponseEntry<E> query(@NonNull AbstractQuery<?, E> query) {
		URI uri = createUri(query);
		HttpGet httpGet = null;
		String responseRaw = null;
		try {
			//Do the request
			//TODO: Figure out how to handle errors with different status codes (causes Exception)
			//TODO: Handle backoff times
			log.debug("Querying API with URL: " + uri);

			httpGet = new HttpGet(uri);
			HttpResponse responseHttp = httpclient.execute(httpGet);

			//Handle errors
			//TODO: More efficent way to do this?
			JsonNode responseTree = jsonMapper.readTree(responseHttp.getEntity().getContent());
			JsonNode errorIdNode = responseTree.get("error_id");
			if (errorIdNode != null)
				//Have an error, throw an exception
				throw new QueryErrorException(uri, responseRaw,
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
			throw new QueryException(uri, responseRaw, "Unable to excute query", e);
		} finally {
			if (httpGet != null)
				httpGet.releaseConnection();
		}
	}

	public static void main(String[] args) throws IOException {
		try {
			//Load up api key
			Properties authProperties = new Properties();
			authProperties.load(StackClient.class.getResourceAsStream("/auth.properties"));
			StackClient client = new StackClient(authProperties.getProperty("seApiKey"));

			//Get posts
			ResponseEntry<AnswerEntry> response = client.query(AnswerQueries.all()
					.setSite("stackoverflow")
					.setFilter("!*2-Ks9DZr4MCSs67uH2q9UHUyUSATRXZkecYeRbMs"));
			log.debug("Got " + response.getItems().size() + " entries");
		} catch (QueryException e) {
			e.printStackTrace();
			System.err.println("RAW: " + e.getRawResponse());
		}
	}

	protected static class LowerEnumDeserializer extends StdScalarDeserializer<Enum<?>> {
		protected LowerEnumDeserializer(Class<Enum<?>> clazz) {
			super(clazz);
		}

		@Override
		public Enum<?> deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			String text = jp.getText().toUpperCase();
			try {
				for (Enum curEnum : (Enum[]) getValueClass().getEnumConstants())
					if (curEnum.name().equals(text))
						return curEnum;
				throw new RuntimeException("Could not find " + text + " in " + getValueClass().getName());
			} catch (Exception e) {
				throw new RuntimeException("Cannot deserialize enum " + getValueClass().getName() + " from " + text, e);
			}
		}
	}
}
