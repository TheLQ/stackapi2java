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
package org.thelq.stackexchange.api.model.types;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Data
public class ShallowUserEntry {
	@MaybeAbsent
	protected Integer userId;
	protected UserType userType;
	@MaybeAbsent
	protected String displayName;
	@MaybeAbsent
	protected Integer acceptRate;
	@MaybeAbsent
	protected String link;
	@MaybeAbsent
	protected String profileImage;
	@MaybeAbsent
	protected Integer reputation;
}
