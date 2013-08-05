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
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
public class InboxItemEntry implements ItemEntry {
	@MaybeAbsent
	protected Integer answerId;
	@MaybeAbsent
	protected Integer commentId;
	@MaybeAbsent
	protected Integer questionId;
	protected String body;
	protected DateTime creationDate;
	protected Boolean unread;
	protected Type itemType;
	protected URI link;
	@MaybeAbsent
	protected SiteEntry site;
	protected String title;

	public enum Type {
		COMMENT,
		CHAT_MESSAGE,
		NEW_ANSWER,
		CAREERS_MESSAGE,
		CAREERS_INVITATIONS,
		META_QUESTION,
		POST_NOTICE,
		MODERATOR_MESSAGE
	}
}
