package org.thelq.stackexchange.api.model.types;

import org.thelq.stackexchange.api.model.MaybeAbsent;
import org.thelq.stackexchange.api.model.types.ShallowUserEntry;
import lombok.Data;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Data
public class CommentEntry implements ItemEntry {
	protected Integer commentId;
	protected Integer postId;
	protected Type postType;
	protected String body;
	protected String bodyMarkdown;
	protected DateTime creationDate;
	protected Boolean edited;
	protected String link;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	@MaybeAbsent
	protected ShallowUserEntry replyToUser;
	protected Integer score;

	protected static enum Type {
		QUESTION,
		ANSWER
	}
}
