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

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Data
@Setter(AccessLevel.NONE)
public class TagWikiEntry implements ItemEntry {
	protected String body;
	protected DateTime bodyLastEditDate;
	protected String excerpt;
	protected DateTime excerptLastEditDate;
	protected ShallowUserEntry lastBodyEditor;
	protected ShallowUserEntry lastExcerptEditor;
	protected String tagName;
}
