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
public class QuestionTimelineEntry implements ItemEntry {
	@MaybeAbsent
	protected Integer questionId;
	@MaybeAbsent
	protected Integer postId;
	@MaybeAbsent
	protected Integer commentId;
	protected DateTime creationDate;
	@MaybeAbsent
	protected Integer downVoteCount;
	@MaybeAbsent
	protected Integer upVoteCount;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	@MaybeAbsent
	protected String revisionGuid;
	protected Type timelinesType;
	@MaybeAbsent
	protected ShallowUserEntry user;

	public static enum Type {
		QUESTION,
		ANSWER,
		COMMENT,
		UNACCEPTED_ANSWER,
		ACCEPTED_ANSWER,
		VOTE_AGGREGATE,
		REVISION,
		POST_STATE_CHANGED
	}
}
