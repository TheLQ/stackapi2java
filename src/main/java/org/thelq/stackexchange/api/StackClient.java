package org.thelq.stackexchange.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Leon
 */
@Slf4j
public class StackClient {
	protected final String seApiKey;
	protected final HttpClient httpclient;

	public StackClient(String seApiKey) throws IOException {
		Preconditions.checkNotNull(seApiKey);
		this.seApiKey = seApiKey;
		
		//Setup httpclient
		this.httpclient = HttpClientBuilder.create()
				.build();
	}

	public ResponseEntry<PostEntry> getRecentPosts(String site) throws Exception {
		return querySE(PostEntry.class, "posts", site);
	}

	protected <E> ResponseEntry<E> querySE(Class<E> itemClass, String method, String site, String... options) throws Exception {
		HttpGet httpGet = null;
		try {
			URIBuilder url = new URIBuilder().setScheme("https").setHost("api.stackexchange.com")
					.setPath("/2.1/" + method)
					.setParameter("site", "stackoverflow")
					.setParameter("intitle", "workaround")
					.setParameter("tagged", "javascript");
			for (String curOption : options)
				url.setParameter(curOption, null);

			//Do the request
			//TODO: Figure out how to handle errors with different status codes (causes Exception)
			//TODO: Handle backoff times
			log.debug("Querying API with URL: " + url);
			httpGet = new HttpGet(url.build());
			HttpResponse responseHttp = httpclient.execute(httpGet);
			String responseRaw = EntityUtils.toString(responseHttp.getEntity());

			//Handle errors 
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> responseMap = mapper.readValue(responseRaw, Map.class);
			if (responseMap.containsKey("error_id"))
				//Have an error, throw an exception
				throw new SEException((int) (Integer) responseMap.get("error_id"),
						(String) responseMap.get("error_name"), (String) responseMap.get("error_message"));

			//No errors, convert what we can to ResponseEntry automatically
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			//DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING 
			ResponseEntry responseEntry = mapper.readValue(responseRaw, new TypeReference<ResponseEntry<PostEntry>>() {
			});

			return responseEntry;
		} catch (Exception e) {
			throw new Exception("Can't query StackExchange", e);
		} finally {
			if (httpGet != null)
				httpGet.releaseConnection();
		}
	}
}
