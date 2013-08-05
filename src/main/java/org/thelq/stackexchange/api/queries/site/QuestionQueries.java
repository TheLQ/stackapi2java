/**
 * Copyright (C) 2013 Leon Blakey <lord.quackstar at gmail.com>
 *
 * This file is part of stackapi2java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public final class QuestionQueries {
	private QuestionQueries() {
		//Do not allow creation
	}

	public static <Q extends BaseComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>> Q all() {
		return new BaseComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions"))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>> Q byTags(@NonNull Collection<String> tags) {
		return new BaseComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byIds(Collection<Integer> questionIds) {
		return new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("questions/{}", questionIds))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byFeatured() {
		return new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/featured"))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byFeatured(@NonNull Collection<String> tags) {
		return new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/featured"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byUnanswered() {
		return new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/unanswered"))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byUnanswered(@NonNull Collection<String> tags) {
		return new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/unanswered"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byNoAnswers() {
		return new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/no-answers"))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q byNoAnswers(@NonNull Collection<String> tags) {
		return new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/no-answers"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags));
	}

	public static <Q extends BaseComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>> Q byLinked(Collection<Integer> questionIds) {
		return new BaseComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("questions/{}/linked", questionIds))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>> Q byRelated(Collection<Integer> questionIds) {
		return new BaseComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("questions/{}/related", questionIds))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q answers(Collection<Integer> questionIds) {
		return new BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, new VectorQueryMethod("questions/{}/answers", questionIds))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q comments(Collection<Integer> questionIds) {
		return new BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("questions/{}/comments", questionIds))
				.self();
	}

	public static <Q extends BaseComplexDateQuery<Q, QuestionTimelineEntry>> Q timeline(Collection<Integer> questionIds) {
		return new BaseComplexDateQuery<Q, QuestionTimelineEntry>(QuestionTimelineEntry.class, new VectorQueryMethod("questions/{}/timeline", questionIds))
				.self();
	}
}
