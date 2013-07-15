/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.Collection;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.SortableField;
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

	public static <Q extends AbstractComplexFullQuery<Integer, ByTaggedSort, Q, QuestionEntry>> Q all() {
		return new AbstractComplexFullQuery<Integer, ByTaggedSort, Q, QuestionEntry>(ByTaggedSort.class, QuestionEntry.class, new SimpleQueryMethod("questions"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<Integer, ByTaggedSort, Q, QuestionEntry>> Q byTags(@NonNull Collection<String> tags) {
		return new AbstractComplexFullQuery<Integer, ByTaggedSort, Q, QuestionEntry>(ByTaggedSort.class, QuestionEntry.class, new SimpleQueryMethod("questions"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>> Q byIds(Collection<Integer> questionIds) {
		return new AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new VectorQueryMethod("questions/{}", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>> Q byFeatured() {
		return new AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/featured"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>> Q byFeatured(@NonNull Collection<String> tags) {
		return new AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/featured"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>> Q byUnanswered() {
		return new AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/unanswered"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>> Q byUnanswered(@NonNull Collection<String> tags) {
		return new AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/unanswered"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>> Q byNoAnswers() {
		return new AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/no-answers"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>> Q byNoAnswers(@NonNull Collection<String> tags) {
		return new AbstractComplexFullQuery<Integer, QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, new SimpleQueryMethod("questions/no-answers"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<Integer, ByRelatedSort, Q, QuestionEntry>> Q byLinked(Collection<Integer> questionIds) {
		return new AbstractComplexFullQuery<Integer, ByRelatedSort, Q, QuestionEntry>(ByRelatedSort.class, QuestionEntry.class, new VectorQueryMethod("questions/{}/linked", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<Integer, ByRelatedSort, Q, QuestionEntry>> Q byRelated(Collection<Integer> questionIds) {
		return new AbstractComplexFullQuery<Integer, ByRelatedSort, Q, QuestionEntry>(ByRelatedSort.class, QuestionEntry.class, new VectorQueryMethod("questions/{}/related", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<Integer, QuestionSort, Q, AnswerEntry>> Q answers(Collection<Integer> questionIds) {
		return new AbstractComplexFullQuery<Integer, QuestionSort, Q, AnswerEntry>(QuestionSort.class, AnswerEntry.class, new VectorQueryMethod("questions/{}/answers", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<Integer, CommentsSort, Q, CommentEntry>> Q comments(Collection<Integer> questionIds) {
		return new AbstractComplexFullQuery<Integer, CommentsSort, Q, CommentEntry>(CommentsSort.class, CommentEntry.class, new VectorQueryMethod("questions/{}/comments", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexDateQuery<Q, QuestionTimelineEntry>> Q timeline(Collection<Integer> questionIds) {
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
