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

import lombok.NonNull;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.types.EventEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class EventQueries {
	public static <Q extends BaseSitePagableQuery<Q, EventEntry>> Q all() {
		return new BaseSitePagableQuery<Q, EventEntry>(EventEntry.class, new SimpleQueryMethod("events"))
				.setAuthRequired(true);
	}

	public static <Q extends BaseSitePagableQuery<Q, EventEntry>> Q all(@NonNull DateTime since) {
		return new BaseSitePagableQuery<Q, EventEntry>(EventEntry.class, new SimpleQueryMethod("events"))
				.setParameter("since", String.valueOf(since.getMillis()))
				.setAuthRequired(true);
	}
}
