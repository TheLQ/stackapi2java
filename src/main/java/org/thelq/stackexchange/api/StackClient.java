package org.thelq.stackexchange.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.thelq.stackexchange.api.exceptions.QueryException;
import org.thelq.stackexchange.api.exceptions.QueryErrorException;
import org.thelq.stackexchange.api.model.ResponseEntry;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.thelq.stackexchange.api.model.AnswerEntry;
import org.thelq.stackexchange.api.model.GenericEntry;
import org.thelq.stackexchange.api.queries.AbstractQuery;
import org.thelq.stackexchange.api.queries.types.AnswerQuery;

/**
 *
 * @author Leon
 */
@Slf4j
public class StackClient {
	protected final String seApiKey;
	protected final HttpClient httpclient;
	protected final ObjectMapper jsonMapper;

	public StackClient(String seApiKey) {
		Preconditions.checkNotNull(seApiKey);
		this.seApiKey = seApiKey;
		this.jsonMapper = new ObjectMapper();
		jsonMapper.registerModule(new JodaModule());
		jsonMapper.registerModule(new GuavaModule());
		jsonMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		jsonMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

		//Setup httpclient
		this.httpclient = HttpClientBuilder.create()
				.build();
	}

	protected <E extends GenericEntry> ResponseEntry<E> query(@NonNull AbstractQuery<?, E> query) {
		Map<String, String> finalParameters = query.buildFinalParameters();

		//Handle potential vectorized methods
		String method = query.getMethod();
		if (method.contains("{}")) {
			for (List<?> curVector : query.getVectors()) {
				String vectorCombined = StringUtils.join(curVector, ",");
				String newMethod = StringUtils.replaceOnce(method, "{}", vectorCombined);
				if (newMethod.equals(method))
					throw new RuntimeException("Too many vectors for " + query.getMethod() + ": " + query.getVectors());
				method = newMethod;
			}
			if (method.contains("{}"))
				throw new RuntimeException("Too few vectors for " + query.getMethod() + ": " + query.getVectors());
		}

		//Build
		URIBuilder uriBuilder = new URIBuilder()
				.setScheme("https")
				.setHost("api.stackexchange.com")
				.setPath("/2.1/" + method);
		if (StringUtils.isNotBlank(seApiKey))
			uriBuilder.setParameter("key", seApiKey);
		for (Map.Entry<String, String> curParam : finalParameters.entrySet())
			uriBuilder.setParameter(curParam.getKey(), curParam.getValue());

		URI uri;
		try {
			uri = uriBuilder.build();
		} catch (URISyntaxException ex) {
			throw new RuntimeException("Cannot build URL");
		}

		HttpGet httpGet = null;
		String responseRaw = null;
		try {
			//Do the request
			//TODO: Figure out how to handle errors with different status codes (causes Exception)
			//TODO: Handle backoff times
			log.debug("Querying API with URL: " + uriBuilder);

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
			
			AnswerEntry entry = client.query(new AnswerQuery().setSite("stackoverflow")).getItems().get(0);
			log.info("There are " );
		} catch (QueryException e) {
			e.printStackTrace();
			System.err.println("RAW: " + e.getRawResponse());
		}
	}
}
