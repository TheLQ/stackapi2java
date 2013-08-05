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

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.SearchSort;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class SearchQueries {
	public static SearchBasicQuery searchBasic() {
		return new SearchBasicQuery();
	}

	public static SearchAdvancedQuery searchAdvanced() {
		return new SearchAdvancedQuery();

	}

	public static <Q extends AbstractSearchQuery<Q>> Q similar(@NonNull String title) {
		return new AbstractSearchQuery<Q>(new SimpleQueryMethod("similar"))
				.setParameter("title", title);
	}

	@Getter
	public static class AbstractSearchQuery<Q extends AbstractSearchQuery<Q>> extends AbstractComplexFullQuery<SearchSort<?>, Q, QuestionEntry> {
		protected final List<String> tagged = new ArrayList<String>();
		protected final List<String> notTagged = new ArrayList<String>();

		protected AbstractSearchQuery(QueryMethod method) {
			super(QuestionEntry.class, method);
		}

		public Q addTagged(@NonNull String tag) {
			tagged.add(tag);
			return self();
		}

		public Q addNotTagged(@NonNull String notTag) {
			notTagged.add(notTag);
			return self();
		}

		@Override
		public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
			Preconditions.checkState(!(!notTagged.isEmpty() && tagged.isEmpty()), "notTagged requires tagged to have at least one tag");
			LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
			if (!tagged.isEmpty())
				finalParameters.put("tagged", QueryUtils.PARAMETER_JOINER.join(tagged));
			if (!notTagged.isEmpty())
				finalParameters.put("notTagged", QueryUtils.PARAMETER_JOINER.join(notTagged));
			return finalParameters;
		}
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	public static class SearchBasicQuery extends AbstractSearchQuery<SearchBasicQuery> {
		protected String inTitle;

		public SearchBasicQuery() {
			super(new SimpleQueryMethod("search"));
		}

		@Override
		public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
			Preconditions.checkState(StringUtils.isNotBlank(inTitle) || !tagged.isEmpty(), "Must specify tag or intitle");
			LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
			QueryUtils.putIfNotNull(finalParameters, "inTitle", inTitle);
			return finalParameters;
		}
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	public static class SearchAdvancedQuery extends AbstractSearchQuery<SearchAdvancedQuery> {
		protected String q;
		protected Boolean accepted;
		protected Boolean answers;
		protected String body;
		protected Boolean closed;
		protected Boolean migrated;
		protected Boolean notice;
		protected String title;
		protected Integer user;
		protected String url;
		protected Integer views;
		protected Boolean wiki;

		public SearchAdvancedQuery() {
			super(new SimpleQueryMethod("search/advanced"));
		}

		@Override
		public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
			LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
			int origSize = finalParameters.size();
			QueryUtils.putIfNotNull(finalParameters, "q", q);
			QueryUtils.putIfNotNull(finalParameters, "accepted", accepted);
			QueryUtils.putIfNotNull(finalParameters, "answers", answers);
			QueryUtils.putIfNotNull(finalParameters, "body", body);
			QueryUtils.putIfNotNull(finalParameters, "closed", closed);
			QueryUtils.putIfNotNull(finalParameters, "migrated", migrated);
			QueryUtils.putIfNotNull(finalParameters, "notice", notice);
			QueryUtils.putIfNotNull(finalParameters, "title", title);
			QueryUtils.putIfNotNull(finalParameters, "user", user);
			QueryUtils.putIfNotNull(finalParameters, "url", url);
			QueryUtils.putIfNotNull(finalParameters, "views", views);
			QueryUtils.putIfNotNull(finalParameters, "wiki", wiki);
			if (origSize == finalParameters.size() && !notTagged.isEmpty())
				throw new IllegalStateException("notTagged requires at least one other parameter to be set");
			return finalParameters;
		}
	}
}
