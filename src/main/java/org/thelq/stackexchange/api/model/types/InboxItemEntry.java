/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
