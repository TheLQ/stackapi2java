/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
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
public class QuestionEntry implements ItemEntry {
	@MaybeAbsent
	protected Integer acceptedAnswerId;
	protected Integer answerCount;
	@MaybeAbsent
	protected ImmutableList<AnswerEntry> answers;
	protected String body;
	@MaybeAbsent
	protected Integer bountyAmount;
	@MaybeAbsent
	protected DateTime BountyClosesDate;
	protected Integer closeVoteCount;
	@MaybeAbsent
	protected DateTime closedDate;
	@MaybeAbsent
	protected String closedReason;
	@MaybeAbsent
	protected ImmutableList<CommentEntry> comments;
	@MaybeAbsent
	protected DateTime communityOwnedDate;
	protected DateTime creationDate;
	protected Integer deleteVoteCount;
	protected Integer downVoteCount;
	protected Integer favoriteCount;
	@JsonProperty("is_answered")
	protected Boolean answered;
	protected DateTime lastActivityDate;
	@MaybeAbsent
	protected DateTime lastEditDate;
	protected URI link;
	@MaybeAbsent
	protected DateTime lockDate;
	@MaybeAbsent
	protected MigrationInfoEntry migrationInfo;
	@MaybeAbsent
	protected MigrationInfoEntry migrationTo;
	protected NoticeEntry notice;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	@MaybeAbsent
	protected DateTime protectedDate;
	protected Integer questionId;
	protected Integer reopenVoteCount;
	protected Integer score;
	protected ImmutableList<String> tags;
	protected String title;
	protected Integer upVoteCount;
	protected Integer viewCount;
}
