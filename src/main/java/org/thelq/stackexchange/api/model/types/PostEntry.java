package org.thelq.stackexchange.api.model.types;

import com.google.common.collect.ImmutableList;
import java.net.URI;
import lombok.Data;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Data
public class PostEntry implements ItemEntry {
	protected Integer postId;
	protected PostType postType;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	protected String body;
	@MaybeAbsent
	protected ImmutableList<CommentEntry> comments;
	protected Integer downVoteCount;
	protected Integer upVoteCount;
	protected DateTime creationDate;
	protected DateTime lastActivityDate;
	@MaybeAbsent
	protected DateTime lastEditDate;
	protected URI link;
	protected Integer score;
}
