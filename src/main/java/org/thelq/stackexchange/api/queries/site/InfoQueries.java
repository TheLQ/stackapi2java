/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.InfoEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;

/**
 *
 * @author Leon
 */
public class InfoQueries {
	public static <Q extends AbstractSiteQuery<Q, InfoEntry>> Q info(@NonNull String site) {
		return new AbstractSiteQuery<Q, InfoEntry>(InfoEntry.class, new SimpleQueryMethod("info"))
				.setSite(site);
	}
}
