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
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.model.types.TagEntry;
import org.thelq.stackexchange.api.model.types.TagSynonymEntry;
import org.thelq.stackexchange.api.model.types.TagWikiEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.TagSort;
import org.thelq.stackexchange.api.queries.site.sort.TagSynonymSort;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class TagQueries {
	public static <Q extends AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>> Q all() {
		return new AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>> Q all(@NonNull String inname) {
		return new AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags"))
				.setParameter("inname", inname);
	}

	public static <Q extends AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>> Q allModeratorOnly() {
		return new AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags/moderator-only"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>> Q allModeratorOnly(@NonNull String inname) {
		return new AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags/moderator-only"))
				.setParameter("inname", inname);
	}

	public static <Q extends AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>> Q allRequired() {
		return new AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags/required"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>> Q allRequired(@NonNull String inname) {
		return new AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags/required"))
				.setParameter("inname", inname);
	}

	public static <Q extends AbstractComplexFullQuery<TagSynonymSort<?>, Q, TagSynonymEntry>> Q allSynonyms() {
		return new AbstractComplexFullQuery<TagSynonymSort<?>, Q, TagSynonymEntry>(TagSynonymEntry.class, new SimpleQueryMethod("tags/synonyms"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>> Q byNames(@NonNull Collection<String> tags) {
		return new AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new VectorQueryMethod("tags/{}/info", tags))
				.self();
	}

	public static <Q extends AbstractSitePagableQuery<Q, QuestionEntry>> Q faqs(@NonNull Collection<String> tags) {
		return new AbstractSitePagableQuery<Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("tags/{}/faq", tags))
				.self();
	}

	public static <Q extends AbstractSitePagableQuery<Q, TagEntry>> Q related(@NonNull Collection<String> tags) {
		return new AbstractSitePagableQuery<Q, TagEntry>(TagEntry.class, new VectorQueryMethod("tags/{}/related", tags))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<TagSort<?>, Q, TagSynonymEntry>> Q synonyms(@NonNull Collection<String> tags) {
		return new AbstractComplexFullQuery<TagSort<?>, Q, TagSynonymEntry>(TagSynonymEntry.class, new VectorQueryMethod("tags/{}/synonyms ", tags))
				.self();
	}

	public static <Q extends AbstractSitePagableQuery<Q, TagWikiEntry>> Q tagWikis(@NonNull Collection<String> tags) {
		return new AbstractSitePagableQuery<Q, TagWikiEntry>(TagWikiEntry.class, new VectorQueryMethod("tags/{}/wikis", tags))
				.self();
	}

	public static <Q extends AbstractSitePagableQuery<Q, TagEntry>> Q topAnswers(@NonNull String tag, Period period) {
		return new AbstractSitePagableQuery<Q, TagEntry>(TagEntry.class, new VectorQueryMethod("tags/{}/top-answers/{}", tag, period.toString()))
				.self();
	}

	public static <Q extends AbstractSitePagableQuery<Q, TagEntry>> Q topAskers(@NonNull String tag, Period period) {
		return new AbstractSitePagableQuery<Q, TagEntry>(TagEntry.class, new VectorQueryMethod("tags/{}/top-askers/{}", tag, period.toString()))
				.self();
	}

	public static enum Period {
		ALL_TIME,
		MONTH
	}
}
