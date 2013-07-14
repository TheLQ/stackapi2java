/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import org.thelq.stackexchange.api.model.SortableField;
import java.util.LinkedHashMap;
import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractComplexFullQuery<F extends Enum<F> & SortableField, Q extends AbstractComplexFullQuery<F, Q, I>, I extends ItemEntry> extends AbstractComplexDateQuery<Q, I> {
	protected final Class<F> enumClass;
	protected Order order;
	protected F sort;
	protected Integer min;
	protected Integer max;

	public AbstractComplexFullQuery(Class<F> enumClass, Class<I> itemClass, QueryMethod method) {
		super(itemClass, method);
		this.enumClass = enumClass;
	}

	public Q setOrder(Order order) {
		this.order = order;
		return self();
	}

	public Q setSort(F sort) {
		this.sort = sort;
		return self();
	}

	public Q setMin(int min) {
		this.min = min;
		return self();
	}

	public Q setMax(int max) {
		this.max = max;
		return self();
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "order", order);
		QueryUtils.putIfNotNull(finalParameters, "sort", sort);
		QueryUtils.putIfNotNull(finalParameters, "min", min);
		QueryUtils.putIfNotNull(finalParameters, "max", max);
		return finalParameters;
	}
}
