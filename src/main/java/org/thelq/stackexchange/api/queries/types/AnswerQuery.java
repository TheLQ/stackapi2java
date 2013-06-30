/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.types;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.thelq.stackexchange.api.model.AnswerEntry;
import org.thelq.stackexchange.api.queries.AbstractComplexQuery;
import org.thelq.stackexchange.api.queries.types.AnswerQuery.SortField;
import org.thelq.stackexchange.api.queries.SortableField;

/**
 *
 * @author Leon
 */
@Getter
public class AnswerQuery extends AbstractComplexQuery<AnswerQuery, SortField> {
	protected final List<Integer> answerIds;

	public AnswerQuery() {
		super(SortField.class, AnswerEntry.class, "answer/{}", new ArrayList<Integer>());
		answerIds = (List<Integer>) vectors.get(0);
	}

	public AnswerQuery addAnswerId(int answerId) {
		answerIds.add(answerId);
		return this;
	}

	public static enum SortField implements SortableField {
		ACTIVITY,
		CREATION,
		VOTES
	}
}
