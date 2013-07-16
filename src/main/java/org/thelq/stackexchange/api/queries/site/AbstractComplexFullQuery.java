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
public class AbstractComplexFullQuery<S extends SortableField<?, S>, Q extends AbstractComplexFullQuery<S, Q, I>, I extends ItemEntry> extends AbstractComplexDateQuery<Q, I> {
	protected S sort;
	protected Order order;

	public AbstractComplexFullQuery(Class<I> itemClass, QueryMethod method) {
		super(itemClass, method);
	}

	public Q setSort(S sort) {
		this.sort = sort;
		return self();
	}

	public Q setOrder(Order order) {
		this.order = order;
		return self();
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "sort", sort);
		QueryUtils.putIfNotNull(finalParameters, "min", sort.convert(sort.getMin()));
		QueryUtils.putIfNotNull(finalParameters, "max", sort.convert(sort.getMax()));
		QueryUtils.putIfNotNull(finalParameters, "order", order);
		return finalParameters;
	}
}
