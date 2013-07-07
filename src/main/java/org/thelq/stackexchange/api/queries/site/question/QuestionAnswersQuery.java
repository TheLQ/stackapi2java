/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.question;

import org.thelq.stackexchange.api.model.sort.AnswerSort;
import org.thelq.stackexchange.api.model.types.AnswerEntry;

/**
 *
 * @author Leon
 */
public class QuestionAnswersQuery extends AbstractQuestionByIdQuery<AnswerSort, QuestionAnswersQuery, AnswerEntry> {
	public QuestionAnswersQuery() {
		super(AnswerSort.class, AnswerEntry.class, "questions/{}/answers ", true);
	}
}
