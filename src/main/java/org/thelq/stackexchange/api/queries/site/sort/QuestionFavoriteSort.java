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
package org.thelq.stackexchange.api.queries.site.sort;

import org.joda.time.DateTime;

/**
 *
 * @author Leon
 */
public class QuestionFavoriteSort<M> extends ResultSort<M, QuestionFavoriteSort<M>> {
	public static QuestionFavoriteSort<DateTime> activity() {
		return new QuestionFavoriteSort<DateTime>("activity");
	}

	public static QuestionFavoriteSort<DateTime> creation() {
		return new QuestionFavoriteSort<DateTime>("creation");
	}

	public static QuestionFavoriteSort<Integer> votes() {
		return new QuestionFavoriteSort<Integer>("votes");
	}

	public static QuestionFavoriteSort<DateTime> added() {
		return new QuestionFavoriteSort<DateTime>("added");
	}

	public QuestionFavoriteSort(String name) {
		super(name);
	}
}
