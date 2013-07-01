/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.info;

import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.site.AbstractSiteQuery;

/**
 *
 * @author Leon
 */
public class InfoQuery extends AbstractSiteQuery<InfoQuery, ItemEntry> {
	public InfoQuery() {
		super(ItemEntry.class, "info");
	}
}
