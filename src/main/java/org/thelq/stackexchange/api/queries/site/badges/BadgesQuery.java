/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.badges;

import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@Getter
public class BadgesQuery extends AbstractBadgesQuery<BadgesQuery> {
	protected String inName;

	public BadgesQuery(Type type) {
		super(type.getMethod());
	}

	public BadgesQuery setGetInName(String inName) {
		this.inName = inName;
		return this;
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "inName", getInName());
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
