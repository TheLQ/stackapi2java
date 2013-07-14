/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Leon
 */
public final class QueryUtils {
	/**
	 * Joiner to create parameter for query. Used instead of StringUtils.join since this
	 * throws NPE's on null elements
	 */
	public static final Joiner PARAMETER_JOINER = Joiner.on(';');

	private QueryUtils() {
		//Do now allow instances
	}

	public static void putIfNotNull(LinkedHashMap<String, String> finalParameters, String key, Object value) {
		if (value == null)
			return;
		String valueString = String.valueOf(value);
		Preconditions.checkArgument(StringUtils.isNotBlank(valueString), "Value cannot be blank");
		finalParameters.put(key, valueString);
	}
}
