/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
public class NotificationEntry implements ItemEntry {
	protected String body;
	protected DateTime creationDate;
	protected Boolean unread;
	protected Type notificationType;
	@MaybeAbsent
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
