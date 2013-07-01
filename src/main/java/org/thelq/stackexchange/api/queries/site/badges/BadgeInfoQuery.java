/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.badges;

import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Leon
 */
@Getter
public class BadgeInfoQuery extends AbstractBadgeInfoQuery<BadgeInfoQuery> {
	protected String inName;

	public BadgeInfoQuery(Type type) {
		super(type.getMethod());
	}

	public BadgeInfoQuery setGetInName(String inName) {
		this.inName = inName;
		return this;
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		putIfNotNull(finalParameters, "inName", getInName());
		return finalParameters;
	}

	@Getter
	@RequiredArgsConstructor
	public static enum Type {
		ALL("badges"),
		NAMED("badges/named"),
		TAG_BASED("badges/tags");
		protected final String method;
	}
}
