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

import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Getter
public class TagWikiEntry implements ItemEntry {
	@MaybeAbsent
	protected String body;
	@MaybeAbsent
	protected DateTime bodyLastEditDate;
	@MaybeAbsent
	protected String excerpt;
	@MaybeAbsent
	protected DateTime excerptLastEditDate;
	@MaybeAbsent
	protected ShallowUserEntry lastBodyEditor;
	@MaybeAbsent
	protected ShallowUserEntry lastExcerptEditor;
	protected String tagName;
}
