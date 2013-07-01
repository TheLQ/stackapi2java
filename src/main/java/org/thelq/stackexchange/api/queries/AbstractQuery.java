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
	protected final LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
	protected final Class<I> itemClass;

	public AbstractQuery(Class<I> itemClass, String method) {
		this.itemClass = itemClass;
		this.method = method;
	}

	public Q setParameter(String key, String value) {
		getParameters().put(key, value);
		return (Q) this;
	}

	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		return new LinkedHashMap<String, String>(parameters);
	}
}
