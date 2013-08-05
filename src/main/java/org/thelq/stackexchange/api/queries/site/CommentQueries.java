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

import java.util.Collection;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.CommentSort;

/**
 *
 * @author Leon
 */
public class CommentQueries {
	public static <Q extends AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q all() {
		return new AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new SimpleQueryMethod("comments"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q byIds(@NonNull Collection<Integer> commentIds) {
		return new AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("comments/{}", commentIds))
				.self();
	}

	public static <Q extends AbstractSiteQuery<Q, CommentEntry>> Q edit(int commentId, @NonNull String body) {
		return new AbstractSiteQuery<Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("comments/{}/edit", String.valueOf(commentId)))
				.setParameter("body", body)
				.setAuthRequired(true);
	}

	public static <Q extends AbstractSiteQuery<Q, CommentEntry>> Q delete(int commentId) {
		return new AbstractSiteQuery<Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("comments/{}/delete", String.valueOf(commentId)))
				.setAuthRequired(true);
	}
}
