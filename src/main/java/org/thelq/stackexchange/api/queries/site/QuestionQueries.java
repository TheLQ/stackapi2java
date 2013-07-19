/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import org.thelq.stackexchange.api.queries.site.sort.CommentSort;
import java.util.Collection;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.model.types.QuestionTimelineEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.QuestionRelatedSort;
import org.thelq.stackexchange.api.queries.site.sort.PostSort;
import org.thelq.stackexchange.api.queries.site.sort.QuestionTaggedSort;

/**
 *
 * @author Leon
 */
public final class QuestionQueries {
	private QuestionQueries() {
		//Do not allow creation
	}

	public static <Q extends AbstractComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>> Q all() {
		return new AbstractComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>> Q byTags(@NonNull Collection<String> tags) {
		return new AbstractComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byIds(Collection<Integer> questionIds) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("questions/{}", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byFeatured() {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/featured"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byFeatured(@NonNull Collection<String> tags) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/featured"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byUnanswered() {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/unanswered"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byUnanswered(@NonNull Collection<String> tags) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/unanswered"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byNoAnswers() {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/no-answers"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byNoAnswers(@NonNull Collection<String> tags) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/no-answers"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends AbstractComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>> Q byLinked(Collection<Integer> questionIds) {
		return new AbstractComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("questions/{}/linked", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>> Q byRelated(Collection<Integer> questionIds) {
		return new AbstractComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("questions/{}/related", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q answers(Collection<Integer> questionIds) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, new VectorQueryMethod("questions/{}/answers", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q comments(Collection<Integer> questionIds) {
		return new AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("questions/{}/comments", questionIds))
				.self();
	}

	public static <Q extends AbstractComplexDateQuery<Q, QuestionTimelineEntry>> Q timeline(Collection<Integer> questionIds) {
		return new AbstractComplexDateQuery<Q, QuestionTimelineEntry>(QuestionTimelineEntry.class, new VectorQueryMethod("questions/{}/timeline", questionIds))
				.self();
	}
}
