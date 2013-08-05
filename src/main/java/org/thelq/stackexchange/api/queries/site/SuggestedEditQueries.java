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
import org.thelq.stackexchange.api.model.types.SuggestedEditEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.SuggestedEditSort;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class SuggestedEditQueries {
	public static <Q extends BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>> Q all() {
		return new BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>(SuggestedEditEntry.class, new SimpleQueryMethod("suggested-edits"))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>> Q byIds(@NonNull Collection<Integer> suggestedEditIds) {
		return new BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>(SuggestedEditEntry.class, new VectorQueryMethod("suggested-edits/{}", suggestedEditIds))
				.self();
	}
}
