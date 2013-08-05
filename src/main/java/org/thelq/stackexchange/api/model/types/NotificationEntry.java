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
public class NotificationEntry implements ItemEntry {
	protected String body;
	protected DateTime creationDate;
	protected Boolean unread;
	protected Type notificationType;
	protected Integer postId;
	protected SiteEntry site;

	public enum Type {
		GENERIC,
		PROFILE_ACTIVITY,
		BOUNTY_EXPIRED,
		BOUNTY_EXPIRES_IN_ONE_DAY,
		BADGE_EARNED,
		BOUNTY_EXPIRES_IN_THREE_DAYS,
		REPUTATION_BONUS,
		ACCOUNTS_ASSOCIATED,
		NEW_PRIVILEGE,
		POST_MIGRATED,
		MODERATOR_MESSAGE,
		REGISTRATION_REMINDER,
		EDIT_SUGGESTED,
		SUBSTANTIVE_EDIT
	}
}
