/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.Getter;
import org.joda.time.DateTime;

/**
 *
 * @author Leon
 */
@Getter
public class RevisionEntry implements ItemEntry {
	@MaybeAbsent
	protected String body;
	protected String comment;
	protected DateTime creationDate;
	@JsonProperty("is_rollback")
	protected boolean rollback;
	@MaybeAbsent
	protected String lastBody;
	@MaybeAbsent
	protected ImmutableList<String> lastTags;
	@MaybeAbsent
	protected String lastTitle;
	protected Integer postId;
	protected PostType postType;
	protected String revisionGuid;
	protected Integer revisionNumber;
	protected Type revisionType;
	@JsonProperty("set_community_wiki")
	protected Boolean communityWiki;
	@MaybeAbsent
	protected List<String> tags;
	@MaybeAbsent
	protected String title;
	protected ShallowUserEntry user;
	
	public static enum Type {
		SINGLE_USER,
		VOTE_BASED;
		
		@JsonValue
		public String jsonName() {
			return name().toLowerCase();
		}
	}
}
