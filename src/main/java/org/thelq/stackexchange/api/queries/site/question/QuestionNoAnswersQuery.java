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
public class QuestionNoAnswersQuery extends AbstractQuestionByTagQuery<QuestionSort, QuestionNoAnswersQuery> {
	public QuestionNoAnswersQuery() {
		super(QuestionSort.class, "questions/no-answers");
	}
}
