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

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Leon
 */
public final class QueryUtils {
	/**
	 * Joiner to create parameter for query. Used instead of StringUtils.join since this
	 * throws NPE's on null elements
	 */
	public static final Joiner PARAMETER_JOINER = Joiner.on(';');

	private QueryUtils() {
		//Do now allow instances
	}

	public static void putIfNotNull(LinkedHashMap<String, String> finalParameters, String key, Object value) {
		if (value == null)
			return;
		String valueString = String.valueOf(value);
		Preconditions.checkArgument(StringUtils.isNotBlank(valueString), "Value cannot be blank");
		finalParameters.put(key, valueString);
	}
}
