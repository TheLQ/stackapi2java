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
import org.thelq.stackexchange.api.queries.site.AbstractComplexQuery;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractAnswerQuery<Q extends AbstractAnswerQuery<Q, I>, I extends GenericEntry> extends AbstractComplexQuery<AnswerSortField, Q, I> {
	protected final List<Integer> answerIds;

	public AbstractAnswerQuery(String method, Class<I> itemClass) {
		super(AnswerSortField.class, itemClass, method, new ArrayList<Integer>());
		answerIds = (List<Integer>) vectors.get(0);
	}

	public Q addAnswerId(int answerId) {
		answerIds.add(answerId);
		return (Q) this;
	}
}
