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
import org.thelq.stackexchange.api.model.BadgeEntry;
import org.thelq.stackexchange.api.model.GenericEntry;
import org.thelq.stackexchange.api.queries.AbstractQuery;
import org.thelq.stackexchange.api.queries.AuthRequiredQuery;
import org.thelq.stackexchange.api.queries.site.badges.BadgeInfoByIdQuery;

/**
 *
 * @author Leon
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
		jsonMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

		//Setup httpclient
		this.httpclient = HttpClientBuilder.create()
				.build();
	}

	protected <E extends GenericEntry> ResponseEntry<E> query(@NonNull AbstractQuery<?, E> query) {
		Map<String, String> finalParameters = query.buildFinalParameters();

		if (query instanceof AuthRequiredQuery && StringUtils.isBlank(accessToken))
			throw new RuntimeException("Query " + query.getClass().getName() + " requires an accessToken");

		//Build
		URIBuilder uriBuilder = new URIBuilder()
				.setScheme("https")
				.setHost("api.stackexchange.com")
				.setPath("/2.1/" + query.getMethod());
		if (StringUtils.isNotBlank(seApiKey))
			uriBuilder.setParameter("key", seApiKey);
		for (Map.Entry<String, String> curParam : finalParameters.entrySet()) {
			if (curParam.getKey() == null || curParam.getValue() == null)
				throw new NullPointerException("Parameters cannot be null: " + curParam.getKey() + "=" + curParam.getValue());
			uriBuilder.setParameter(curParam.getKey(), curParam.getValue());
		}

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

			ResponseEntry<BadgeEntry> response = client.query(new BadgeInfoByIdQuery().addBadgeId(94).setParameter("min", "5").setSite("stackoverflow"));
			log.debug("Got " + response.getItems().size() + " badges");

		} catch (QueryException e) {
			e.printStackTrace();
			System.err.println("RAW: " + e.getRawResponse());
		}
	}
}
