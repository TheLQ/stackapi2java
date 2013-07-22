/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model.types;

import java.net.URI;
import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;

/**
 *
 * @author Leon
 */
@Getter
public class ReputationEntry implements ItemEntry {
	protected URI link;
	protected DateTime onDate;
	protected Integer postId;
	protected PostType postType;
	protected Integer reputationChange;
	protected String title;
	protected Integer userId;
	protected ReputationVoteType voteType;
}
