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
import org.thelq.stackexchange.api.model.types.PostEntry;
import org.thelq.stackexchange.api.model.types.RevisionEntry;
import org.thelq.stackexchange.api.model.types.SuggestedEditEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.CommentSort;
import org.thelq.stackexchange.api.queries.site.sort.PostSort;
import org.thelq.stackexchange.api.queries.site.sort.SuggestedEditSort;

/**
 *
 * @author Leon
 */
public class PostQueries {
	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, PostEntry>> Q all() {
		return new AbstractComplexFullQuery<PostSort<?>, Q, PostEntry>(PostEntry.class, new SimpleQueryMethod("posts"))
				.self();
	}
	
	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, PostEntry>> Q byIds(@NonNull Collection<Integer> postIds) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, PostEntry>(PostEntry.class, new VectorQueryMethod("posts/{}", postIds))
				.self();
	}
	
	public static <Q extends AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q comments(@NonNull Collection<Integer> postIds) {
		return new AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("posts/{}/comments", postIds))
				.self();
	}
	
	public static <Q extends AbstractSiteQuery<Q, CommentEntry>> Q commentAdd(int postId, @NonNull String body) {
		return new AbstractSiteQuery<Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("posts/{}/comments/add", String.valueOf(postId)))
				.setParameter("body", body)
				.setAuthRequired(true);
	}
	
	public static <Q extends AbstractComplexDateQuery<Q, RevisionEntry>> Q revisions(@NonNull Collection<Integer> postIds) {
		return new AbstractComplexDateQuery<Q, RevisionEntry>(RevisionEntry.class, new VectorQueryMethod("posts/{}/revisions", postIds))
				.self();
	}
	
	public static <Q extends AbstractComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>> Q suggestedEdits(@NonNull Collection<Integer> postIds) {
		return new AbstractComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>(SuggestedEditEntry.class, new VectorQueryMethod("posts/{}/suggested-edits", postIds))
				.self();
	}
}
