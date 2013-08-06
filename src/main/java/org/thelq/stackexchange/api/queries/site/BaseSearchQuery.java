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
import java.util.LinkedHashMap;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.SearchSort;

/**
 *
 * @author Leon
 */
@Getter
public class BaseSearchQuery<Q extends BaseSearchQuery<Q>> extends BaseComplexFullQuery<SearchSort<?>, Q, QuestionEntry> {
	protected final List<String> tagged = new ArrayList<String>();
	protected final List<String> notTagged = new ArrayList<String>();

	protected BaseSearchQuery(QueryMethod method) {
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
