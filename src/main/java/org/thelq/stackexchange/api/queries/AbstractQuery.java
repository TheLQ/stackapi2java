/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import javax.management.Query;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Leon
 */
@Getter
public abstract class AbstractQuery<Q extends AbstractQuery<Q>> {
	protected String site;
	protected String method;
	protected final LinkedHashMap<String, String> parameters = new ParameterLinkedHashMap();
	protected final Class itemClass;

	public AbstractQuery(Class itemClass) {
		this.itemClass = itemClass;
	}

	public Q setSite(String site) {
		this.site = site;
		return (Q) this;
	}

	public Q setMethod(String method) {
		this.method = method;
		return (Q) this;
	}

	public Q setParameter(String key, String value) {
		getParameters().put(key, value);
		return (Q) this;
	}

	protected static class ParameterLinkedHashMap extends LinkedHashMap<String, String> {
		@Override
		public String put(String key, String value) {
			Preconditions.checkArgument(StringUtils.isNotBlank(key), "Must specify key");
			if (value != null)
				return super.put(key, value);
			//Value is null, auto remove
			return remove(key);
		}
	}
}
