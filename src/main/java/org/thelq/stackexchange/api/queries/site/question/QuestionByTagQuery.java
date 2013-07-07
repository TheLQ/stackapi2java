/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.question;

import java.util.LinkedHashMap;
import org.thelq.stackexchange.api.model.sort.QuestionByTagSort;

/**
 *
 * @author Leon
 */
public class QuestionByTagQuery extends AbstractQuestionByTagQuery<QuestionByTagSort, QuestionByTagQuery> {
	public QuestionByTagQuery() {
		super(QuestionByTagSort.class, "questions");
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		if (getSort() == QuestionByTagSort.HOT || getSort() == QuestionByTagSort.WEEK || getSort() == QuestionByTagSort.MONTH)
			//API doesn't allow min or max values
			if (getMin() != null || getMax() != null)
				throw new RuntimeException("Sorts by HOT, WEEK, or MONTH cannot have Max or Min constraints");
		return super.buildFinalParameters();
	}
}
