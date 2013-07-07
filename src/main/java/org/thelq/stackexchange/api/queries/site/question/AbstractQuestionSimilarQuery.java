/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.question;

import java.util.LinkedHashMap;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.sort.QuestionBySimilarSort;
import org.thelq.stackexchange.api.model.types.QuestionEntry;

/**
 *
 * @author Leon
 */
public class AbstractQuestionSimilarQuery<Q extends AbstractQuestionSimilarQuery<Q>> extends AbstractQuestionByIdQuery<QuestionBySimilarSort, Q, QuestionEntry> {
	public AbstractQuestionSimilarQuery(String method) {
		super(QuestionBySimilarSort.class, QuestionEntry.class, method, true);
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		if (getSort() == QuestionBySimilarSort.RANK && (getMin() != null || getMax() != null))
			throw new IllegalStateException("QuestionByLinkedSort cannot have min or max");
		return super.buildFinalParameters();
	}
}
