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

import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;

/**
 * Marker interface for a field that is sortable
 * @author Leon
 */
@Getter
@RequiredArgsConstructor
public abstract class ResultSort<M, F extends ResultSort<M, F>> {
	protected final String name;
	protected M min;
	protected M max;

	public F setMin(M min) {
		this.min = min;
		return self();
	}

	public F setMax(M max) {
		this.max = max;
		return self();
	}

	/**
	 * Convert the value to a String. By default this is hardcoded to use 
	 * {@link DateTime#getMillis() } for dates and {@link #toString() } for 
	 * everything else
	 * @param value A stored value
	 * @return String version of that value
	 */
	public String convert(Object value) {
		if (value == null)
			return null;
		else if (value instanceof DateTime)
			return String.valueOf(((DateTime) value).getMillis());
		return value.toString();
	}

	public F self() {
		return (F) this;
	}
}
