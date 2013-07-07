/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.question;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.thelq.stackexchange.api.model.types.QuestionTimelineEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractComplexDateQuery;

/**
 *
 * @author Leon
 */
//TODO: Somehow get under AbstractQuestionComplexFullQuery?
@Getter
public class QuestionTimelineQuery extends AbstractComplexDateQuery<QuestionTimelineQuery, QuestionTimelineEntry> {
	protected final List<Integer> questionIds = new ArrayList<Integer>();

	public QuestionTimelineQuery() {
		super(QuestionTimelineEntry.class, "questions/{}/timeline");
	}

	public QuestionTimelineQuery addQuestionId(int questionId) {
		questionIds.add(questionId);
		return this;
	}

	@Override
	public String getMethod() {
		Preconditions.checkState(!getQuestionIds().isEmpty(), "Must specify at least one id");
		return QueryUtils.insertVector(getMethod(), questionIds);
	}
}
