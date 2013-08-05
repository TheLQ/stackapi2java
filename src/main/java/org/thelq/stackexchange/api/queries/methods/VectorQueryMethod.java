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
package org.thelq.stackexchange.api.queries.methods;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@Getter
public class VectorQueryMethod implements QueryMethod {
	protected final String raw;
	protected final String[] vectorSingle;
	protected final Collection<?> vectorCollection;

	public VectorQueryMethod(@NonNull String raw, @NonNull String... vectorSingle) {
		Preconditions.checkArgument(StringUtils.countMatches(raw, "{}") == 1, "Raw method does not contain vector");
		this.raw = raw;
		this.vectorSingle = vectorSingle;
		this.vectorCollection = null;
	}

	public VectorQueryMethod(@NonNull String raw, @NonNull Collection<?> vectorCollection) {
		Preconditions.checkArgument(StringUtils.countMatches(raw, "{}") == 1, "Raw method does not contain vector");
		Preconditions.checkArgument(!vectorCollection.isEmpty(), "Vector collection is empty");
		Preconditions.checkArgument(vectorCollection.size() < 100, "Vectors do not support more than 100 elements");
		this.raw = raw;
		this.vectorSingle = null;
		this.vectorCollection = vectorCollection;
	}

	public String getFinal() {
		String methodFinal = raw;
		if (vectorSingle != null) {
			String[] subsitutions = new String[vectorSingle.length];
			Arrays.fill(subsitutions, "{}");
			methodFinal = StringUtils.replaceEach(raw, subsitutions, vectorSingle);
		} else if (vectorCollection != null) {
			if (StringUtils.countMatches(raw, "{}") != 1)
				throw new RuntimeException("No more vectors to replace! Raw: " + raw + " | Final: " + methodFinal);
			//Verify vector if it is required
			Iterator<?> vectorItr = vectorCollection.iterator();
			if (!vectorItr.hasNext())
				throw new RuntimeException("Vector cannot be empty");

			//Do the replace
			methodFinal = StringUtils.replaceOnce(raw, "{}", QueryUtils.PARAMETER_JOINER.join(vectorItr));

			//Make sure we didn't miss anything
			if (methodFinal.contains("{}"))
				throw new RuntimeException("Still contains vector: " + methodFinal);
		} else
			throw new RuntimeException("No vector to replace! " + raw);
		return methodFinal;
	}
}
