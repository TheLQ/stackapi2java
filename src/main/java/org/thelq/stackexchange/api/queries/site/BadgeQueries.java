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
import org.thelq.stackexchange.api.model.types.BadgeEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.BadgeSort;
import org.thelq.stackexchange.api.queries.site.sort.BadgeTypeSort;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class BadgeQueries {
	public static <Q extends BaseComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>> Q all() {
		return new BaseComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges"))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>> Q all(@NonNull String inname) {
		return new BaseComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges"))
				.setParameter("inname", inname);
	}

	public static <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q allNamed() {
		return new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/name"))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q allNamed(@NonNull String inname) {
		return new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/name"))
				.setParameter("inname", inname);
	}

	public static <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q allTagBased() {
		return new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.self();
	}

	public static <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q allTagBased(@NonNull String inname) {
		return new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.setParameter("inname", inname);
	}
	
	public static <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q byIds(@NonNull Collection<Integer> badgeIds) {
		return new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new VectorQueryMethod("badges/tags", badgeIds))
				.self();
	}
	
	public static <Q extends BaseComplexDateQuery<Q, BadgeEntry>> Q recipients() {
		return new BaseComplexDateQuery<Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.self();
	}
	
	public static <Q extends BaseComplexDateQuery<Q, BadgeEntry>> Q recipientsByIds(@NonNull Collection<Integer> badgeIds) {
		return new BaseComplexDateQuery<Q, BadgeEntry>(BadgeEntry.class, new VectorQueryMethod("badges/tags", badgeIds))
				.self();
	}
}
