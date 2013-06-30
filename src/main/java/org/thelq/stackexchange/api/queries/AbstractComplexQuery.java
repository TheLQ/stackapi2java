/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 *
 * @author Leon
 */
public abstract class AbstractComplexQuery<Q extends AbstractComplexQuery<Q, F>, F extends Enum<F> & SortableField> extends AbstractPagableQuery<Q> {
	protected final Class<F> enumClass;
	public AbstractComplexQuery(Class itemClass, Class<F> enumClass) {
		super(itemClass);
		this.enumClass = enumClass;
	}

	public DateTime getFromDate() {
		return new DateTime(Integer.valueOf(getParameters().get("fromdate")), DateTimeZone.UTC);
	}

	public Q setFromDate(DateTime fromDate) {
		setParameter("fromdate", String.valueOf(fromDate.getMillis()));
		return (Q) this;
	}

	public DateTime getToDate() {
		return new DateTime(Integer.valueOf(getParameters().get("todate")), DateTimeZone.UTC);
	}

	public Q setToDate(DateTime toDate) {
		setParameter("todate", String.valueOf(toDate.getMillis()));
		return (Q) this;
	}

	public Order getOrder() {
		return Order.valueOf(getParameters().get("order"));
	}

	public Q setOrder(Order order) {
		setParameter("order", order.toString());
		return (Q) this;
	}

	public F getSort() {
		return Enum.valueOf(enumClass, getParameters().get("sort"));
	}

	public Q setSort(F sort) {
		setParameter("sort", sort.toString());
		return (Q) this;
	}

	public Integer getMin() {
		return Integer.parseInt(getParameters().get("min"));
	}

	public Q setMin(int min) {
		setParameter("min", String.valueOf(min));
		return (Q) this;
	}
	
	public Integer getMax() {
		return Integer.parseInt(getParameters().get("max"));
	}

	public Q setMax(int max) {
		setParameter("max", String.valueOf(max));
		return (Q) this;
	}
}
