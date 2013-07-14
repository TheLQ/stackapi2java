/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.LinkedHashMap;
import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractComplexDateQuery<Q extends AbstractComplexDateQuery<Q, I>, I extends ItemEntry> extends AbstractSitePagableQuery<Q, I> {
	protected DateTime toDate;
	protected DateTime fromDate;

	public AbstractComplexDateQuery(Class<I> itemClass, String method) {
		super(itemClass, method);
	}

	public Q setFromDate(DateTime fromDate) {
		this.fromDate = fromDate;
		return self();
	}

	public Q setToDate(DateTime toDate) {
		this.toDate = toDate;
		return self();
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		if (fromDate != null)
			finalParameters.put("fromDate", String.valueOf(fromDate.getMillis()));
		if (toDate != null)
			finalParameters.put("toDate", String.valueOf(toDate.getMillis()));
		return finalParameters;
	}
}
