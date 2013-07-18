/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import lombok.NonNull;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.types.EventEntry;
import org.thelq.stackexchange.api.queries.AuthRequiredQuery;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;

/**
 *
 * @author Leon
 */
public class EventQueries {
	public static <Q extends AbstractSitePagableQuery<Q, EventEntry>> Q all() {
		return new EventQuery<Q>(EventEntry.class, new SimpleQueryMethod("events"))
				.self();
	}

	public static <Q extends AbstractSitePagableQuery<Q, EventEntry>> Q all(@NonNull DateTime since) {
		return new EventQuery<Q>(EventEntry.class, new SimpleQueryMethod("events"))
				.setParameter("since", String.valueOf(since.getMillis()));
	}

	protected static class EventQuery<Q extends AbstractSitePagableQuery<Q, EventEntry>> extends AbstractSitePagableQuery<Q, EventEntry> implements AuthRequiredQuery {
		public EventQuery(Class<EventEntry> itemClass, QueryMethod method) {
			super(itemClass, method);
		}
	}
}
