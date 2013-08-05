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
import java.util.Collections;
import lombok.NonNull;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class UserQueryUtils {
	public static Collection<Integer> ME_IDS = Collections.unmodifiableCollection(Collections.<Integer>emptyList());
	public static Integer ME_ID = new Integer(0);

	protected static boolean isMe(Integer userId) {
		return userId == ME_ID;
	}

	protected static boolean isMe(Collection<Integer> userIds) {
		return userIds == ME_IDS;
	}

	protected static SimpleQueryMethod createUserQueryMethod(@NonNull String userRaw, @NonNull Integer userId) {
		if (isMe(userId))
			return new SimpleQueryMethod("me/" + userRaw);
		else
			return new SimpleQueryMethod("users/" + userId + "/" + userRaw);
	}

	protected static VectorQueryMethod createUserQueryMethod(@NonNull String userRaw, @NonNull Integer userId, @NonNull Collection<?> vectorCollection) {
		if (isMe(userId))
			return new VectorQueryMethod("me/" + userRaw, vectorCollection);
		else
			return new VectorQueryMethod("users/" + userId + "/" + userRaw, vectorCollection);
	}

	protected static QueryMethod createUserQueryMethod(@NonNull String userRaw, @NonNull Collection<Integer> userIds, @NonNull String... vectorSingle) {
		if (isMe(userIds))
			if (vectorSingle.length == 0)
				return new SimpleQueryMethod("me/" + userRaw);
			else
				return new VectorQueryMethod("me/" + userRaw, vectorSingle);
		else
			return new VectorQueryMethod("users/{}/" + userRaw, vectorSingle);
	}
}
