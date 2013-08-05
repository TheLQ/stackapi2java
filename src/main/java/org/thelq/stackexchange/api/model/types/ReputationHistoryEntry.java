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

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Getter
public class ReputationHistoryEntry implements ItemEntry {
	protected DateTime date;
	protected Integer postId;
	protected Integer reputationChange;
	protected Type reputationHistoryType;
	protected Integer userId;

	public static enum Type {
		ASKER_ACCEPTS_ANSWER,
		ASKER_UNACCEPT_ANSWER,
		ANSWER_ACCEPTED,
		ANSWER_UNACCEPTED,
		VOTER_DOWNVOTES,
		VOTER_UNDOWNVOTES,
		POST_DOWNVOTED,
		POST_UNDOWNVOTED,
		POST_UPVOTED,
		POST_UNUPVOTED,
		SUGGESTED_EDIT_APPROVAL_RECEIVED,
		POST_FLAGGED_AS_SPAM,
		POST_FLAGGED_AS_OFFENSIVE,
		BOUNTY_GIVEN,
		BOUNTY_EARNED,
		BOUNTY_CANCELLED,
		POST_DELETED,
		POST_UNDELETED,
		ASSOCIATION_BONUS,
		ARBITRARY_REPUTATION_CHANGE,
		VOTE_FRAUD_REVERSAL,
		POST_MIGRATED,
		USER_DELETED
	}
}
