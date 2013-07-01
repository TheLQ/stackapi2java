/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AnswerEntry extends ResponseEntry<AnswerEntry> implements ItemEntry {
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
	@JsonProperty("is_accepted")
	protected boolean accepted;
	protected String link;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	protected ImmutableList<String> tags;
	
}
