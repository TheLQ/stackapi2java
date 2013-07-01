/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import com.google.common.base.Joiner;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Leon
 */
public final class QueryUtils {
	/**
	 * Global joiner for vectors. Used instead of StringUtils.join() since this
	 * will throw an NPE when a null element is encountered
	 */
	protected static final Joiner VECTOR_JOINER = Joiner.on(',');

	private QueryUtils() {
		//Do now allow instances
	}

	public static void putIfNotNull(LinkedHashMap<String, String> finalParameters, String key, Object value) {
		if (value != null)
			finalParameters.put(key, String.valueOf(value));
	}

	public static String insertVector(String method, List<?> vector1) {
		if (!method.contains("{}"))
			throw new RuntimeException("No vector to replace in method " + method);
		String vectorCombined = VECTOR_JOINER.join(vector1);
		return StringUtils.replaceOnce(method, "{}", vectorCombined);
	}
	
	public static String insertVector(String method, List<?> vector1, List<?> vector2) {
		return insertVector(insertVector(method, vector1), vector2);
	}
}
