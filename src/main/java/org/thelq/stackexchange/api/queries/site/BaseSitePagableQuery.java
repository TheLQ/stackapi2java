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
import lombok.NonNull;
import org.thelq.stackexchange.api.StackClient;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.PagableQuery;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Getter
public class BaseSitePagableQuery<Q extends BaseSitePagableQuery<Q, I>, I extends ItemEntry> extends BaseSiteQuery<Q, I> implements PagableQuery<Q, I> {
	protected Integer page;
	protected Integer pageSize;
	public BaseSitePagableQuery(Class<I> itemClass, QueryMethod method) {
		super(itemClass, method);
	}

	@Override
	public Q setPage(int page) {
		this.page = page;
		return self();
	}

	@Override
	public Q setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return self();
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "page", page);
		QueryUtils.putIfNotNull(finalParameters, "pageSize", pageSize);
		return finalParameters;
	}
	
	public StackClient.QueryIterable<I> queryIterable(@NonNull StackClient client) {
		return client.queryIterable(this);
	}

	public StackClient.QueryIterable<I> queryIterable(StackClient client, int maxPages) {
		return client.queryIterable(this, maxPages);
	}
}
