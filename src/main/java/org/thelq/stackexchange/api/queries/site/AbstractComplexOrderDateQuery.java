/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractComplexOrderDateQuery<Q extends AbstractComplexOrderDateQuery<Q, I>, I extends ItemEntry> extends AbstractComplexDateQuery<Q, I> {
	protected Order order;

	public AbstractComplexOrderDateQuery(Class<I> itemClass, QueryMethod method) {
		super(itemClass, method);
	}

	public Q setOrder(Order order) {
		this.order = order;
		return self();
	}
}
