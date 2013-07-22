/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model.types;

import org.thelq.stackexchange.api.model.ItemEntry;

/**
 *
 * @author Leon
 */
public class WritePermissionEntry implements ItemEntry {
	protected Boolean canAdd;
	protected Boolean canDelete;
	protected Boolean canEdit;
	protected Integer maxDailyActions;
	protected Integer minSecondsBetweenActions;
	protected String objectType;
	protected Integer userId;
}
