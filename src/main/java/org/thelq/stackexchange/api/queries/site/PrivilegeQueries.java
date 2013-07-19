/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import org.thelq.stackexchange.api.model.types.PrivlegeEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;

/**
 *
 * @author Leon
 */
public class PrivilegeQueries {
	public <Q extends AbstractSitePagableQuery<Q, PrivlegeEntry>> Q all() {
		return new AbstractSitePagableQuery<Q, PrivlegeEntry>(PrivlegeEntry.class, new SimpleQueryMethod("privileges"))
				.self();
	}
}
