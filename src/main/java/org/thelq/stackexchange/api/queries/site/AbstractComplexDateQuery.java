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
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractComplexDateQuery<Q extends AbstractComplexDateQuery<Q, I>, I extends ItemEntry> extends AbstractSitePagableQuery<Q, I> {
	protected DateTime toDate;
	protected DateTime fromDate;

	public AbstractComplexDateQuery(Class<I> itemClass, QueryMethod method) {
		super(itemClass, method);
	}

	public Q setFromDate(DateTime fromDate) {
		this.fromDate = fromDate;
		return self();
	}

	public Q setToDate(DateTime toDate) {
		this.toDate = toDate;
		return self();
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		if (fromDate != null)
			finalParameters.put("fromDate", String.valueOf(fromDate.getMillis()));
		if (toDate != null)
			finalParameters.put("toDate", String.valueOf(toDate.getMillis()));
		return finalParameters;
	}
}
