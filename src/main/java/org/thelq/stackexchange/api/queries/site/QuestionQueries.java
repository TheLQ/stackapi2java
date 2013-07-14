/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.SortableField;
import org.thelq.stackexchange.api.model.sort.CommentsSort;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.model.types.QuestionTimelineEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
public final class QuestionQueries {
	private QuestionQueries() {
		//Do not allow creation
	}

	public static <Q extends AbstractComplexFullQuery<ByTaggedSort, Q, QuestionEntry>> Q all() {
		return new AbstractComplexFullQuery<ByTaggedSort, Q, QuestionEntry>(ByTaggedSort.class, QuestionEntry.class, "questions")
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<ByTaggedSort, Q, QuestionEntry>> Q byTags(Iterable<String> tags) {
		return new AbstractComplexFullQuery<ByTaggedSort, Q, QuestionEntry>(ByTaggedSort.class, QuestionEntry.class, "questions")
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static AbstractComplexFullQuery<QuestionSort, ?, QuestionEntry> byIds(final Iterable<Integer> questionIds) {
		return new QuestionByIdComplexQuery<QuestionSort, QuestionEntry>(QuestionSort.class, QuestionEntry.class, "questions/{}", questionIds);
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byFeatured() {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, "questions/featured")
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byFeatured(Iterable<String> tags) {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, "questions/featured")
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byUnanswered() {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, "questions/unanswered")
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byUnanswered(Iterable<String> tags) {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, "questions/unanswered")
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byNoAnswers() {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, "questions/no-answers")
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>> Q byNoAnswers(Iterable<String> tags) {
		return new AbstractComplexFullQuery<QuestionSort, Q, QuestionEntry>(QuestionSort.class, QuestionEntry.class, "questions/no-answers")
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static AbstractComplexFullQuery<ByRelatedSort, ?, QuestionEntry> byLinked(final Iterable<Integer> questionIds) {
		return new QuestionByIdComplexQuery<ByRelatedSort, QuestionEntry>(ByRelatedSort.class, QuestionEntry.class, "questions/{}/linked", questionIds);
	}

	public static AbstractComplexFullQuery<ByRelatedSort, ?, QuestionEntry> byRelated(final Iterable<Integer> questionIds) {
		return new QuestionByIdComplexQuery<ByRelatedSort, QuestionEntry>(ByRelatedSort.class, QuestionEntry.class, "questions/{}/related", questionIds);
	}

	public static AbstractComplexFullQuery<QuestionSort, ?, AnswerEntry> answers(final Iterable<Integer> questionIds) {
		return new QuestionByIdComplexQuery<QuestionSort, AnswerEntry>(QuestionSort.class, AnswerEntry.class, "questions/{}/answers", questionIds);
	}

	public static AbstractComplexFullQuery<CommentsSort, ?, CommentEntry> comments(final Iterable<Integer> questionIds) {
		return new QuestionByIdComplexQuery<CommentsSort, CommentEntry>(CommentsSort.class, CommentEntry.class, "questions/{}/comments", questionIds);
	}

	public static <Q extends AbstractComplexDateQuery<Q, QuestionTimelineEntry>> Q timeline(final Iterable<Integer> questionIds) {
		return new AbstractComplexDateQuery<Q, QuestionTimelineEntry>(QuestionTimelineEntry.class, "questions/{}/timeline") {
			@Override
			public String getMethodFinal() {
				return QueryUtils.insertVector(super.getMethodFinal(), questionIds);
			}
		}.self();
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

	//protected static class QuestionByIdComplexQuery<F extends Enum<F> & SortableField, Q extends QuestionByIdComplexQuery<F, Q, I>, I extends ItemEntry> extends AbstractComplexFullQuery<F, Q, I> {
	protected static class QuestionByIdComplexQuery<F extends Enum<F> & SortableField,  I extends ItemEntry> extends AbstractComplexFullQuery<F, QuestionByIdComplexQuery<F, I>, I> {
		protected final Iterable<Integer> questionIds;

		public QuestionByIdComplexQuery(Class<F> enumClass, Class<I> itemClass, String method, Iterable<Integer> questionIds) {
			super(enumClass, itemClass, method);
			this.questionIds = questionIds;
		}

		@Override
		public String getMethodFinal() {
			return QueryUtils.insertVector(super.getMethodFinal(), questionIds);
		}
		
		
	}
}
