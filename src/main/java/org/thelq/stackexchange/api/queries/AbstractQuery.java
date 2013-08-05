/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import java.util.LinkedHashMap;
import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;

/**
 *
 * @author Leon
 */
@Getter
public abstract class AbstractQuery<Q extends AbstractQuery<Q, I>, I extends ItemEntry> {
	protected final QueryMethod method;
	protected final LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
	protected final Class<I> itemClass;
	protected boolean authRequired = false;

	public AbstractQuery(Class<I> itemClass, QueryMethod method) {
		this.itemClass = itemClass;
		this.method = method;
	}

	public Q setParameter(String key, String value) {
		getParameters().put(key, value);
		return self();
	}

	public Q setParameter(String key, long value) {
		return setParameter(key, String.valueOf(value));
	}

	public Q setAuthRequired(boolean authRequired) {
		this.authRequired = authRequired;
		return self();
	}

	@SuppressWarnings("unchecked")
	public Q self() {
		return (Q) this;
	}

	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		return new LinkedHashMap<String, String>(parameters);
	}
}
