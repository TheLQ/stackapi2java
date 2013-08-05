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

import org.thelq.stackexchange.api.queries.site.sort.ResultOrder;
import org.thelq.stackexchange.api.queries.site.sort.ResultSort;
import java.util.LinkedHashMap;
import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractComplexFullQuery<S extends ResultSort, Q extends AbstractComplexFullQuery<S, Q, I>, I extends ItemEntry> extends AbstractComplexDateQuery<Q, I> {
	protected S sort;
	protected ResultOrder order;

	public AbstractComplexFullQuery(Class<I> itemClass, QueryMethod method) {
		super(itemClass, method);
	}

	public Q setSort(S sort) {
		this.sort = sort;
		return self();
	}

	public Q setOrder(ResultOrder order) {
		this.order = order;
		return self();
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		if (sort != null) {
			finalParameters.put("sort", sort.getName());
			QueryUtils.putIfNotNull(finalParameters, "min", sort.convert(sort.getMin()));
			QueryUtils.putIfNotNull(finalParameters, "max", sort.convert(sort.getMax()));
		}
		QueryUtils.putIfNotNull(finalParameters, "order", order);
		return finalParameters;
	}
}
