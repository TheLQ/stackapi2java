/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.question;

/**
 *
 * @author Leon
 */
public class QuestionRelatedQuery extends AbstractQuestionSimilarQuery<QuestionRelatedQuery> {
	public QuestionRelatedQuery() {
		super("questions/{}/related");
	}
}
