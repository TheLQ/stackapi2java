/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import lombok.NonNull;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.types.EventEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;

/**
 *
 * @author Leon
 */
public class EventQueries {
	public static <Q extends AbstractSitePagableQuery<Q, EventEntry>> Q all() {
		return new AbstractSitePagableQuery<Q, EventEntry>(EventEntry.class, new SimpleQueryMethod("events"))
				.setAuthRequired();
	}

	public static <Q extends AbstractSitePagableQuery<Q, EventEntry>> Q all(@NonNull DateTime since) {
		return new AbstractSitePagableQuery<Q, EventEntry>(EventEntry.class, new SimpleQueryMethod("events"))
				.setParameter("since", String.valueOf(since.getMillis()))
				.setAuthRequired();
	}
}
