package org.thelq.stackexchange.api.model;

import org.thelq.stackexchange.api.model.MaybeAbsent;
import org.thelq.stackexchange.api.model.ShallowUserEntry;
import lombok.Data;

/**
 *
 * @author Leon
 */
@Data
public class CommentEntry implements ItemEntry {
	protected int commentId;
	protected int postId;
	protected Type postType;
	protected String body;
	protected String bodyMarkdown;
	protected long creationDate;
	protected boolean edited;
	protected String link;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	@MaybeAbsent
	protected ShallowUserEntry replyToUser;
	protected int score;

	protected static enum Type {
		QUESTION,
		ANSWER
	}
}
