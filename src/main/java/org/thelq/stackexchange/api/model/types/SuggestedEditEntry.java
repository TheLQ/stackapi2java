/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model.types;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Getter
public class SuggestedEditEntry implements ItemEntry {
	protected Integer suggestedEditId;
	@MaybeAbsent
	protected DateTime approvalDate;
	@MaybeAbsent
	protected String body;
	protected String comment;
	protected DateTime creationDate;
	protected Integer postId;
	protected PostType postType;
	@MaybeAbsent
	protected ShallowUserEntry proposingUser;
	@MaybeAbsent
	protected DateTime rejectionDate;
	@MaybeAbsent
	protected ImmutableList<String> tags;
	@MaybeAbsent
	protected String title;
}
