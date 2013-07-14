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
public class AbstractComplexFullQuery<F extends Enum<F> & SortableField, Q extends AbstractComplexFullQuery<F, Q, I>, I extends ItemEntry> extends AbstractComplexSortedQuery<F, Q, I> {
	protected Integer min;
	protected Integer max;

	public AbstractComplexFullQuery(Class<F> enumClass, Class<I> itemClass, QueryMethod method) {
		super(enumClass, itemClass, method);
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
		QueryUtils.putIfNotNull(finalParameters, "min", min);
		QueryUtils.putIfNotNull(finalParameters, "max", max);
		return finalParameters;
	}
}
