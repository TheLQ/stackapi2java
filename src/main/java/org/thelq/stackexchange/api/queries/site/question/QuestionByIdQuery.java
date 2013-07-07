/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.question;

import org.thelq.stackexchange.api.model.sort.AnswerSort;
import org.thelq.stackexchange.api.model.sort.QuestionSort;
import org.thelq.stackexchange.api.model.types.QuestionEntry;

/**
 *
 * @author Leon
 */
public class QuestionByIdQuery extends AbstractQuestionByIdQuery<QuestionSort, QuestionByIdQuery, QuestionEntry> {
	public QuestionByIdQuery() {
		super(QuestionSort.class, QuestionEntry.class, "questions/{}", false);
	}
}
