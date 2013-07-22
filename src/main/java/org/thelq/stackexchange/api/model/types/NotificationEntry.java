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
	protected NotificationType notificationType;
	@MaybeAbsent
	protected Integer postId;
	protected SiteEntry site;
}
