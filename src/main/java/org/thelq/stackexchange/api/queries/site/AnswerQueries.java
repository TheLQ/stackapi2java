/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.Collection;
import org.thelq.stackexchange.api.model.sort.AnswerSort;
import org.thelq.stackexchange.api.model.sort.CommentsSort;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;

/**
 *
 * @author Leon
 */
public class AnswerQueries {
	public static <Q extends AbstractComplexFullQuery<AnswerSort, Q, AnswerEntry>> Q all() {
		return new AbstractComplexFullQuery<AnswerSort, Q, AnswerEntry>(AnswerSort.class, AnswerEntry.class, new SimpleQueryMethod("answers"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<AnswerSort, Q, AnswerEntry>> Q byIds(Collection<Integer> answerIds) {
		return new AbstractComplexFullQuery<AnswerSort, Q, AnswerEntry>(AnswerSort.class, AnswerEntry.class, new VectorQueryMethod("answers/{}", answerIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<CommentsSort, Q, CommentEntry>> Q comments(Collection<Integer> answerIds) {
		return new AbstractComplexFullQuery<CommentsSort, Q, CommentEntry>(CommentsSort.class, CommentEntry.class, new VectorQueryMethod("answers/{}/comments", answerIds))
				.self();
	}
}
