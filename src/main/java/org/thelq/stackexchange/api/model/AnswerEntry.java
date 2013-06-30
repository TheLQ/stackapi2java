/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

/**
 *
 * @author Leon
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AnswerEntry extends ResponseEntry<AnswerEntry> {
	protected long answerId;
	protected long questionId;
	protected String title;
	protected String body;
	@MaybeAbsent
	protected ImmutableList<CommentEntry> comments;
	@MaybeAbsent
	protected DateTime communityOwnedDate;
	protected DateTime creationDate;
	protected DateTime lastActivityDate;
	@MaybeAbsent
	protected DateTime lastEditDate;
	protected DateTime lockDate;
	protected int score;
	protected int downVoteCount;
	protected int upVoteCount;
	protected boolean isAccepted;
	protected String link;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	protected ImmutableList<String> tags;
	
}