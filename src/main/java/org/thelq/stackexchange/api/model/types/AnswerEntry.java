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
@ToString
@EqualsAndHashCode
public class AnswerEntry implements ItemEntry {
	protected Integer answerId;
	protected Integer questionId;
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
	protected Integer score;
	protected Integer downVoteCount;
	protected Integer upVoteCount;
	@JsonProperty("is_accepted")
	protected Boolean accepted;
	protected String link;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	protected ImmutableList<String> tags;
	
}
