/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model.types;

import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;

/**
 *
 * @author Leon
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
