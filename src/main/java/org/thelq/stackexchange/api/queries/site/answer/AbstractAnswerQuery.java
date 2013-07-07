/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.answer;

import org.thelq.stackexchange.api.model.sort.AnswerSort;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractComplexFullQuery;

/**
 *
 * @author Leon
 */
@Getter
public abstract class AbstractAnswerQuery<Q extends AbstractAnswerQuery<Q, I>, I extends ItemEntry> extends AbstractComplexFullQuery<AnswerSort, Q, I> {
	protected final List<Integer> answerIds;

	public AbstractAnswerQuery(String method, Class<I> itemClass) {
		super(AnswerSort.class, itemClass, method);
		answerIds = new ArrayList<Integer>();
	}

	public Q addAnswerId(int answerId) {
		answerIds.add(answerId);
		return (Q) this;
	}

	@Override
	public String getMethod() {
		return QueryUtils.insertVector(super.getMethod(), answerIds);
	}
}
