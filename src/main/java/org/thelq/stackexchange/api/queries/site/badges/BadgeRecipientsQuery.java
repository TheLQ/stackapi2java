/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.queries.site.badges;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.thelq.stackexchange.api.model.BadgeEntry;
import org.thelq.stackexchange.api.queries.site.AbstractComplexDateQuery;

/**
 *
 * @author Leon
 */
//TODO: Merge with AbstractBadgeInfoById?
public class BadgeRecipientsQuery extends AbstractComplexDateQuery<BadgeRecipientsQuery, BadgeEntry> {
	protected final List<Integer> badgeIds;
	public BadgeRecipientsQuery() {
		super(BadgeEntry.class, "badges/{}/recipients", new ArrayList<Integer>());
		badgeIds = (List<Integer>)vectors.get(0);
	}
	
	public BadgeRecipientsQuery addBadgeId(int badgeId) {
		badgeIds.add(badgeId);
		return this;
	}
	
	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(!badgeIds.isEmpty(), "Must add at least 1 badge id");
		return super.buildFinalParameters();
	}
}
