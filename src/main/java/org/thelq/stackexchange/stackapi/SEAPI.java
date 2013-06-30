package org.thelq.stackexchange.stackapi;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.Module.SetupContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.http.HttpClient;

/**
 *
 * @author Leon
 */
public class SEAPI {
	protected static final Logger log = LoggerFactory.getLogger(SEAPI.class);
	protected final String seApiKey;
	protected static SEAPI instance;
	protected HttpClient httpclient = new DecompressingHttpClient(new DefaultHttpClient());

	static {
		try {
			instance = new SEAPI();
		} catch (IOException ex) {
			log.error("FATAL EXCEPTION: Can't load config.properties", ex);
			System.exit(2);
		}
	}

	public static SEAPI get() {
		return instance;
	}

	public SEAPI() throws IOException {
		Properties config = new Properties();
		config.load(getClass().getResourceAsStream("/config.properties"));
		seApiKey = config.getProperty("se_api_key");
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
			mapper.registerModule(new EnumCaseInsensitiveModule());
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

	public static class LowerEnumDeserializer extends StdScalarDeserializer<Enum<?>> {
		protected LowerEnumDeserializer(Class<Enum<?>> clazz) {
			super(clazz);
		}

		@Override
		public Enum<?> deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			String text = jp.getText().toUpperCase();
			try {
				Method valueOfMethod = getValueClass().getDeclaredMethod("valueOf", String.class);
				return (Enum<?>) valueOfMethod.invoke(null, text);
			} catch (Exception e) {
				throw new RuntimeException("Cannot deserialize enum " + getValueClass().getName() + " from " + text, e);
			}
		}
	}

	public static class EnumCaseInsensitiveModule extends SimpleModule {
		public EnumCaseInsensitiveModule() {
			super("stackarchive-se", new Version(1, 0, 0, "", "org.thelq", "stackarchive-se"));
		}

		@Override
		public void setupModule(SetupContext context) {
			super.setupModule(context);
			Base deser = new Deserializers.Base() {
				@SuppressWarnings("unchecked")
				@Override
				public JsonDeserializer<?> findEnumDeserializer(Class<?> type,
						DeserializationConfig config, BeanDescription beanDesc)
						throws JsonMappingException {
					return new LowerEnumDeserializer((Class<Enum<?>>) type);
				}
			};
			context.addDeserializers(deser);
		}
	}
}
