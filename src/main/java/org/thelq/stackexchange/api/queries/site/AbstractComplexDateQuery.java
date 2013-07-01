/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.List;
import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.GenericEntry;

/**
 *
 * @author Leon
 */
@Getter
public abstract class AbstractComplexDateQuery<Q extends AbstractComplexDateQuery<Q, I>, I extends GenericEntry> extends AbstractSitePagableQuery<Q, I> {
	protected DateTime toDate;
	protected DateTime fromDate;

	public AbstractComplexDateQuery(Class<I> itemClass, String method) {
		super(itemClass, method);
	}

	public Q setFromDate(DateTime fromDate) {
		this.fromDate = fromDate;
		return (Q) this;
	}

	public Q setToDate(DateTime toDate) {
		this.toDate = toDate;
		return (Q) this;
	}
}
