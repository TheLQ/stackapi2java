/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.badges;

import java.util.List;
import org.thelq.stackexchange.api.model.BadgeEntry;
import org.thelq.stackexchange.api.queries.site.AbstractComplexQuery;

/**
 *
 * @author Leon
 */
public class AbstractBadgeInfoQuery<Q extends AbstractBadgeInfoQuery<Q>> extends AbstractComplexQuery<BadgeSortField, Q, BadgeEntry> {
	public AbstractBadgeInfoQuery(String method, List<Integer>... vectors) {
		super(BadgeSortField.class, BadgeEntry.class, method, vectors);
	}
}
