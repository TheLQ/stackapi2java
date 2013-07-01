/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import org.thelq.stackexchange.api.model.types.PrivlegeEntry;

/**
 *
 * @author Leon
 */
public class PrivlegesQuery extends AbstractSitePagableQuery<PrivlegesQuery, PrivlegeEntry> {
	public PrivlegesQuery() {
		super(PrivlegeEntry.class, "privileges");
	}
}
