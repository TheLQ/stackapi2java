/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model.types;

import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Getter
public class TagSynonymEntry implements ItemEntry {
	protected Integer appliedCount;
	protected DateTime creationDate;
	protected String fromTag;
	@MaybeAbsent
	protected DateTime lastAppliedDate;
	protected String toTag;
}
