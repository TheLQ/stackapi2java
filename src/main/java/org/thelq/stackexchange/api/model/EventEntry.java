/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model;

import java.net.URI;
import lombok.Getter;
import org.joda.time.DateTime;

/**
 *
 * @author Leon
 */
@Getter
public class EventEntry implements ItemEntry {
	protected DateTime creationDate;
	protected int eventId;
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
