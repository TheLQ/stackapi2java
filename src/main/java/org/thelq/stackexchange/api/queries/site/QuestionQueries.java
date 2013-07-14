/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import org.thelq.stackexchange.api.model.SortableField;
import org.thelq.stackexchange.api.model.sort.CommentsSort;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.model.types.QuestionTimelineEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;

/**
 *
 * @author Leon
 */
public final class QuestionQueries {
	private QuestionQueries() {
		//Do not allow creation
	}

	public static <Q extends AbstractComplexFullQuery<ByTaggedSort, Q, QuestionEntry>> Q all() {
		return new AbstractComplexFullQuery<ByTaggedSort, Q, QuestionEntry>(ByTaggedSort.class, QuestionEntry.class, new SimpleQueryMethod("questions"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<ByTaggedSort, Q, QuestionEntry>> Q byTags(Iterable<String> tags) {
		return new AbstractComplexFullQuery<ByTaggedSort, Q, QuestionEntry>(ByTaggedSort.class, QuestionEntry.class, new SimpleQueryMethod("questions"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byIds(final Iterable<Integer> questionIds) {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new VectorQueryMethod("questions/{}", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byFeatured() {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/featured"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byFeatured(Iterable<String> tags) {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/featured"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byUnanswered() {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/unanswered"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byUnanswered(Iterable<String> tags) {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/unanswered"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byNoAnswers() {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/no-answers"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byNoAnswers(Iterable<String> tags) {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/no-answers"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<ByRelatedSort, Q, QuestionEntry>> Q byLinked(final Iterable<Integer> questionIds) {
		return new AbstractComplexFullQuery<ByRelatedSort, Q, QuestionEntry>(ByRelatedSort.class, QuestionEntry.class, new VectorQueryMethod("questions/{}/linked", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<ByRelatedSort, Q, QuestionEntry>> Q byRelated(final Iterable<Integer> questionIds) {
		return new AbstractComplexFullQuery<ByRelatedSort, Q, QuestionEntry>(ByRelatedSort.class, QuestionEntry.class, new VectorQueryMethod("questions/{}/related", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, AnswerEntry>> Q answers(final Iterable<Integer> questionIds) {
		return new AbstractComplexFullQuery<QuestionSort, Q, AnswerEntry>(QuestionSort.class, AnswerEntry.class, new VectorQueryMethod("questions/{}/answers", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<CommentsSort, Q, CommentEntry>> Q comments(final Iterable<Integer> questionIds) {
		return new AbstractComplexFullQuery<CommentsSort, Q, CommentEntry>(CommentsSort.class, CommentEntry.class, new VectorQueryMethod("questions/{}/comments", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexDateQuery<Q, QuestionTimelineEntry>> Q timeline(final Iterable<Integer> questionIds) {
		return new AbstractComplexDateQuery<Q, QuestionTimelineEntry>(QuestionTimelineEntry.class, new VectorQueryMethod("questions/{}/timeline", questionIds))
				.self();
	}

	public static enum QuestionSort implements SortableField {
		ACTIVITY,
		CREATION,
		VOTES,
	}

	public static enum ByRelatedSort implements SortableField {
		ACTIVITY,
		CREATION,
		VOTES,
		/**
		 * No min-max
		 */
		RANK
	}

	public static enum ByTaggedSort implements SortableField {
		ACTIVITY,
		CREATION,
		VOTES,
		/**
		 * No min-max
		 */
		HOT,
		/**
		 * No min-max
		 */
		WEEK,
		/**
		 * No min-max
		 */
		MONTH
	}
}
