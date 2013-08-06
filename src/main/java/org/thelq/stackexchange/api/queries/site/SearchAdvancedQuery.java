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

import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;

/**
 *
 * @author Leon
 */
@Getter
@Setter
@Accessors(chain = true)
public class SearchAdvancedQuery extends BaseSearchQuery<SearchAdvancedQuery> {
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
