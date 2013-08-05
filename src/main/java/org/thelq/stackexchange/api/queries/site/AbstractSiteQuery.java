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
import java.util.LinkedHashMap;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.AbstractQuery;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Getter
public class AbstractSiteQuery<Q extends AbstractSiteQuery<Q, I>, I extends ItemEntry> extends AbstractQuery<Q, I> {
	protected String site;

	public AbstractSiteQuery(Class<I> itemClass, QueryMethod method) {
		super(itemClass, method);
	}

	public Q setSite(String site) {
		this.site = site;
		return self();
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(StringUtils.isNotBlank(getSite()), "Must specify site");
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "site", site);
		return finalParameters;
	}
}
