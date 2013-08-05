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

import java.awt.Color;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Represents some stylings of a site in the Stack Exchange network.
 * @see http://api.stackexchange.com/docs/types/styling
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Data
@Setter(AccessLevel.NONE)
//TODO: rename
public class StyleEntry {
	protected Color linkColor;
	protected Color tagForegroundColor;
	protected Color tagBackgroundColor;
}
