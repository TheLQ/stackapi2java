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
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.PostSort;

/**
 * Utility factory for <code>/answers</code> queries
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class AnswerQueries {
	/**
	 * Get all answers on the site
	 * @see <a href="https://api.stackexchange.com/docs/answers">StackExchange API /answers usage documentation</a>
	 * @return Generated configurable query
	 */
	public static <Q extends BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q all() {
		return new BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, new SimpleQueryMethod("answers"))
				.self();
	}

	/**
	 * Get answers that have the specified id
	 * @see <a href="https://api.stackexchange.com/docs/answers-by-ids">StackExchange API /answers/{} usage documentation</a>
	 * @param answerIds Non-null, non-empty collection of answer ids
	 * @return Generated configurable query
	 */
	public static <Q extends BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q byIds(Collection<Integer> answerIds) {
		return new BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, new VectorQueryMethod("answers/{}", answerIds))
				.self();
	}

	/**
	 * Get all comments for the specified answer ids
	 * @see <a href="https://api.stackexchange.com/docs/comments-on-answers">StackExchange API /answers/{}/comments usage documentation</a>
	 * @param answerIds Non-null, non-empty collection of answer ids
	 * @return Generated configurable query
	 */
	public static <Q extends BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q comments(Collection<Integer> answerIds) {
		return new BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("answers/{}/comments", answerIds))
				.self();
	}
}
