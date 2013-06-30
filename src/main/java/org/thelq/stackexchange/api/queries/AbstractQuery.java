/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import com.google.common.collect.ImmutableList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.model.GenericEntry;

/**
 *
 * @author Leon
 */
@Getter
public abstract class AbstractQuery<Q extends AbstractQuery<Q, I>, I extends GenericEntry> {
	protected final String method;
	protected final ImmutableList<List<?>> vectors;
	protected final LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
	protected final Class<I> itemClass;

	public AbstractQuery(Class<I> itemClass, String method, List<?>... vectors) {
		this.itemClass = itemClass;
		this.method = method;
		this.vectors = ImmutableList.copyOf(vectors);
	}

	public Q setParameter(String key, String value) {
		getParameters().put(key, value);
		return (Q) this;
	}

	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		return new LinkedHashMap<String, String>(parameters);
	}

	protected static void putIfNotNull(LinkedHashMap<String, String> finalParameters, String key, Object value) {
		if (value != null)
			finalParameters.put(key, String.valueOf(value));
	}

	protected static void putIfNotNull(LinkedHashMap<String, String> finalParameters, String key, Object valueRaw, Object valueConvert) {
		if (valueRaw != null)
			finalParameters.put(key, String.valueOf(valueConvert));
	}
}
