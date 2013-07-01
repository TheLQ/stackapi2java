/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model.types;

import java.net.URI;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
public class RelatedSiteEntry implements ItemEntry {
	@MaybeAbsent
	protected String apiSiteParameter;
	protected String name;
	protected Relation relation;
	protected URI siteUrl;

	public static enum Relation {
		PARENT,
		META,
		CHAT
	}
}
