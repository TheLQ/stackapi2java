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
public class QuestionFeaturedQuery extends AbstractQuestionByTagQuery<QuestionSort, QuestionFeaturedQuery> {
	public QuestionFeaturedQuery() {
		super(QuestionSort.class, "questions/featured");
	}
}
