/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.LinkedHashMap;
import lombok.Getter;
import org.thelq.stackexchange.api.model.GenericEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@Getter
public abstract class AbstractComplexFullQuery<F extends Enum<F> & SortableField, Q extends AbstractComplexFullQuery<F, Q, I>, I extends GenericEntry> extends AbstractComplexDateQuery<Q, I> {
	protected final Class<F> enumClass;
	protected Order order;
	protected F sort;
	protected Integer min;
	protected Integer max;

	public AbstractComplexFullQuery(Class<F> enumClass, Class<I> itemClass, String method) {
		super(itemClass, method);
		this.enumClass = enumClass;
	}

	public Q setOrder(Order order) {
		this.order = order;
		return (Q) this;
	}

	public Q setSort(F sort) {
		this.sort = sort;
		return (Q) this;
	}

	public Q setMin(int min) {
		this.min = min;
		return (Q) this;
	}

	public Q setMax(int max) {
		this.max = max;
		return (Q) this;
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		if (fromDate != null)
			finalParameters.put("fromDate", String.valueOf(fromDate.getMillis()));
		if (toDate != null)
			finalParameters.put("toDate", String.valueOf(toDate.getMillis()));
		QueryUtils.putIfNotNull(finalParameters, "order", order);
		QueryUtils.putIfNotNull(finalParameters, "sort", sort);
		QueryUtils.putIfNotNull(finalParameters, "min", min);
		QueryUtils.putIfNotNull(finalParameters, "max", max);
		return finalParameters;
	}
}