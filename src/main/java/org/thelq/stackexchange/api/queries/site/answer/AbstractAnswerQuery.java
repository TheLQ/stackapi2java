/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.answer;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.thelq.stackexchange.api.model.AnswerEntry;
import org.thelq.stackexchange.api.model.GenericEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractComplexFullQuery;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractAnswerQuery<Q extends AbstractAnswerQuery<Q, I>, I extends GenericEntry> extends AbstractComplexFullQuery<AnswerSort, Q, I> {
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