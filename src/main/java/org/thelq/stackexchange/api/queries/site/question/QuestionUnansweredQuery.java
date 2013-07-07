/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.question;

import org.thelq.stackexchange.api.model.sort.QuestionSort;

/**
 *
 * @author Leon
 */
public class QuestionUnansweredQuery extends AbstractQuestionByTagQuery<QuestionSort, QuestionUnansweredQuery> {
	public QuestionUnansweredQuery() {
		super(QuestionSort.class, "questions/unanswered");
	}
}
