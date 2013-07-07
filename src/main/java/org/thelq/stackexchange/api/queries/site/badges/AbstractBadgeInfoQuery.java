/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.badges;

import org.thelq.stackexchange.api.model.sort.BadgeSort;
import java.util.List;
import org.thelq.stackexchange.api.model.types.BadgeEntry;
import org.thelq.stackexchange.api.queries.site.AbstractComplexFullQuery;

/**
 *
 * @author Leon
 */
public abstract class AbstractBadgeInfoQuery<Q extends AbstractBadgeInfoQuery<Q>> extends AbstractComplexFullQuery<BadgeSort, Q, BadgeEntry> {
	public AbstractBadgeInfoQuery(String method) {
		super(BadgeSort.class, BadgeEntry.class, method);
	}
}
