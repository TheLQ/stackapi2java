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
public class TagWikiEntry implements ItemEntry {
	@MaybeAbsent
	protected String body;
	@MaybeAbsent
	protected DateTime bodyLastEditDate;
	@MaybeAbsent
	protected String excerpt;
	@MaybeAbsent
	protected DateTime excerptLastEditDate;
	@MaybeAbsent
	protected ShallowUserEntry lastBodyEditor;
	@MaybeAbsent
	protected ShallowUserEntry lastExcerptEditor;
	protected String tagName;
}
