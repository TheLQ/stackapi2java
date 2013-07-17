/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import org.thelq.stackexchange.api.queries.site.sort.CommentsSort;
import java.util.Collection;
import org.thelq.stackexchange.api.queries.site.sort.AnswerSort;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;

/**
 * Utility factory for <code>/answers</code> queries
 * @author Leon
 */
public class AnswerQueries {
	/**
	 * Get all answers on the site
	 * @see <a href="https://api.stackexchange.com/docs/answers">StackExchange API /answers usage documentation</a>
	 * @return Generated configurable query
	 */
	public static <Q extends AbstractComplexFullQuery<AnswerSort<?>, Q, AnswerEntry>> Q all() {
		return new AbstractComplexFullQuery<AnswerSort<?>, Q, AnswerEntry>(AnswerEntry.class, new SimpleQueryMethod("answers"))
				.self();
	}

	/**
	 * Get answers that have the specified id
	 * @see <a href="https://api.stackexchange.com/docs/answers-by-ids">StackExchange API /answers/{} usage documentation</a>
	 * @param answerIds Non-null, non-empty collection of answer ids
	 * @return Generated configurable query
	 */
	public static <Q extends AbstractComplexFullQuery<AnswerSort<?>, Q, AnswerEntry>> Q byIds(Collection<Integer> answerIds) {
		return new AbstractComplexFullQuery<AnswerSort<?>, Q, AnswerEntry>(AnswerEntry.class, new VectorQueryMethod("answers/{}", answerIds))
				.self();
	}

	/**
	 * Get all comments for the specified answer ids
	 * @see <a href="https://api.stackexchange.com/docs/comments-on-answers">StackExchange API /answers/{}/comments usage documentation</a>
	 * @param answerIds Non-null, non-empty collection of answer ids
	 * @return Generated configurable query
	 */
	public static <Q extends AbstractComplexFullQuery<CommentsSort<?>, Q, CommentEntry>> Q comments(Collection<Integer> answerIds) {
		return new AbstractComplexFullQuery<CommentsSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("answers/{}/comments", answerIds))
				.self();
	}
}
