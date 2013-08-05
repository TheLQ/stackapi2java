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
package org.thelq.stackexchange.api.queries;

import java.util.LinkedHashMap;
import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Getter
public abstract class AbstractQuery<Q extends AbstractQuery<Q, I>, I extends ItemEntry> {
	protected final QueryMethod method;
	protected final LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
	protected final Class<I> itemClass;
	protected boolean authRequired = false;
	protected String filter;

	public AbstractQuery(Class<I> itemClass, QueryMethod method) {
		this.itemClass = itemClass;
		this.method = method;
	}

	public Q setParameter(String key, String value) {
		getParameters().put(key, value);
		return self();
	}

	public Q setParameter(String key, long value) {
		return setParameter(key, String.valueOf(value));
	}

	public Q setAuthRequired(boolean authRequired) {
		this.authRequired = authRequired;
		return self();
	}

	public Q setFilter(String filter) {
		this.filter = filter;
		return self();
	}

	@SuppressWarnings("unchecked")
	public Q self() {
		return (Q) this;
	}

	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = new LinkedHashMap<String, String>(parameters);
		finalParameters.put("filter", filter);
		return finalParameters;
	}
}
