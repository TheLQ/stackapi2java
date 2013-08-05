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

import java.net.URI;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.thelq.stackexchange.api.model.ItemEntry;

/**
 * Represents a badge on a Stack Exchange site.
 * @see https://api.stackexchange.com/docs/types/badge
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Data
@Setter(AccessLevel.NONE)
public class BadgeEntry implements ItemEntry {
	protected Integer badgeId;
	protected Integer awardCount;
	protected Type badgeType;
	protected String description;
	protected URI link;
	protected String name;
	protected Rank rank;
	protected ShallowUserEntry user;

	/**
	 * All Badge Types.
	 */
	public static enum Type {
		NAMED,
		TAG_BASED;
	}

	/**
	 * All Badge ranks.
	 */
	public static enum Rank {
		GOLD,
		SILVER,
		BRONZE;
	}
}
