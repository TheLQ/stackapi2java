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
public class TagSort<M> extends ResultSort<M, TagSort<M>> {
	public static TagSort<Integer> popular() {
		return new TagSort<Integer>("popular");
	}

	public static TagSort<DateTime> activity() {
		return new TagSort<DateTime>("activity");
	}

	public static TagSort<String> name() {
		return new TagSort<String>("name");
	}

	public TagSort(String name) {
		super(name);
	}
}