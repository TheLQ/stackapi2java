/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model.types;

import lombok.AccessLevel;
import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Getter
public class TagEntry implements ItemEntry {
	protected Integer count;
	@Getter(AccessLevel.NONE)
	protected Boolean hasSynonyms;
	protected Boolean moderatorOnly;
	protected Boolean required;
	@MaybeAbsent
	protected DateTime lastActivityDate;
	protected String name;
	@MaybeAbsent
	protected Integer userId;
	
	public Boolean hasSynonyms() {
		return hasSynonyms;
	}
}
