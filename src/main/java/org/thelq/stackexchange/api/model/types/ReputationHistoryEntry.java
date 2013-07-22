/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model.types;

import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;

/**
 *
 * @author Leon
 */
@Getter
public class ReputationHistoryEntry implements ItemEntry {
	protected DateTime date;
	protected Integer postId;
	protected Integer reputationChange;
	protected ReputationHistoryType reputationHistoryType;
	protected Integer userId;
}
