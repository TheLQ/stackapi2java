/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.events;

import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.EventEntry;
import org.thelq.stackexchange.api.queries.AuthRequiredQuery;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractSitePagableQuery;

/**
 *
 * @author Leon
 */
@Getter
@Setter
public class EventQuery extends AbstractSitePagableQuery<EventQuery, EventEntry> implements AuthRequiredQuery {
	protected DateTime since;

	public EventQuery() {
		super(EventEntry.class, "events");
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		if (since != null)
			finalParameters.put("since", String.valueOf(since.getMillis()));
		return finalParameters;
	}
}
