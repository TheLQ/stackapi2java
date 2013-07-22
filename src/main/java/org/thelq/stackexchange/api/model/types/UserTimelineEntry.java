/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model.types;

import java.net.URI;
import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Getter
public class UserTimelineEntry implements ItemEntry {
	@MaybeAbsent
	protected Integer badgeId;
	@MaybeAbsent
	protected Integer commentId;
	@MaybeAbsent
	protected Integer suggestedEditId;
	@MaybeAbsent
	protected Integer postId;
	protected Integer userId;
	protected DateTime creationDate;
	@MaybeAbsent
	protected String detail;
	protected URI link;
	protected PostType postType;
	protected UserTimelineType timelineType;
	@MaybeAbsent
	protected String title;
}
