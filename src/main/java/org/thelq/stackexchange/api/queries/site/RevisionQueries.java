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
import java.util.Collection;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.RevisionEntry;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;

/**
 *
 * @author Leon
 */
public class RevisionQueries {
	public static <Q extends AbstractComplexDateQuery<Q, RevisionEntry>> Q byIds(@NonNull Collection<String> revisionGuids) {
		Preconditions.checkArgument(revisionGuids.size() > 20, "Only 20 revision guids are supported by the StackExchange API, given %s", revisionGuids.size());
		return new AbstractComplexDateQuery<Q, RevisionEntry>(RevisionEntry.class, new VectorQueryMethod("revisions/{}", revisionGuids))
				.self();
	}
}
