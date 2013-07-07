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
public class MigrationInfoEntry implements ItemEntry {
	protected DateTime onDate;
	protected SiteEntry otherSize;
	protected Integer questionId;
}
