/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;

/**
 * Marker interface for a field that is sortable
 * @author Leon
 */
@Getter
@RequiredArgsConstructor
public abstract class ResultSort<M, F extends ResultSort<M, F>> {
	protected final String name;
	protected M min;
	protected M max;

	public F setMin(M min) {
		this.min = min;
		return self();
	}

	public F setMax(M max) {
		this.max = max;
		return self();
	}

	/**
	 * Convert the value to a String. By default this is hardcoded to use 
	 * {@link DateTime#getMillis() } for dates and {@link #toString() } for 
	 * everything else
	 * @param value A stored value
	 * @return String version of that value
	 */
	public String convert(Object value) {
		if (value instanceof DateTime)
			return String.valueOf(((DateTime) value).getMillis());
		return value.toString();
	}

	public F self() {
		return (F) this;
	}
}
