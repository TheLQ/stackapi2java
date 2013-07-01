/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.badges;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractSiteQuery;

/**
 *
 * @author Leon
 */
public class BadgeInfoByIdQuery extends AbstractBadgeInfoQuery<BadgeInfoByIdQuery> {
	protected final List<Integer> badgeIds;

	public BadgeInfoByIdQuery() {
		super("badges/{}/recipients");
		badgeIds = new ArrayList<Integer>();
	}

	public BadgeInfoByIdQuery addBadgeId(int badgeId) {
		badgeIds.add(badgeId);
		return this;
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(!badgeIds.isEmpty(), "Must add at least 1 badge id");
		return super.buildFinalParameters();
	}

	@Override
	public String getMethod() {
		return QueryUtils.insertVector(super.getMethod(), badgeIds);
	}
}
