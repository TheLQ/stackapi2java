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
public class EventEntry implements ItemEntry {
	protected DateTime creationDate;
	protected Integer eventId;
	protected Type eventType;
	@MaybeAbsent
	protected String Excerpt;
	protected URI link;

	public static enum Type {
		QUESTION_POSTED,
		ANSWER_POSTED,
		COMMENT_POSTED,
		POST_EDITED,
		USER_CREATED;
	}
}
