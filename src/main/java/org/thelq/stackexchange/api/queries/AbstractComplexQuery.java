/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.GenericEntry;

/**
 *
 * @author Leon
 */
public abstract class AbstractComplexQuery<F extends Enum<F> & SortableField, Q extends AbstractComplexQuery<F, Q, I>, I extends GenericEntry> extends AbstractSiteQuery<Q, I> {
	protected final Class<F> enumClass;
	protected DateTime toDate;
	protected DateTime fromDate;
	protected Order order;
	protected F sort;
	protected Integer min;
	protected Integer max;

	public AbstractComplexQuery(Class<F> enumClass, Class itemClass, String method, List<?>... vectors) {
		super(itemClass, method, vectors);
		this.enumClass = enumClass;
	}

	public Q setFromDate(DateTime fromDate) {
		this.fromDate = fromDate;
		return (Q) this;
	}

	public Q setToDate(DateTime toDate) {
		this.toDate = toDate;
		return (Q) this;
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
		putIfNotNull(finalParameters, "order", order);
		putIfNotNull(finalParameters, "sort", sort);
		putIfNotNull(finalParameters, "min", min);
		putIfNotNull(finalParameters, "max", max);
		return finalParameters;
	}
}
