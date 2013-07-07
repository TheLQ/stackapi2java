/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.badges;

import java.util.LinkedHashMap;
import lombok.Getter;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractBadgesByInNameQuery<Q extends AbstractBadgesByInNameQuery<Q>> extends AbstractBadgesQuery<Q> {
	protected String inName;

	public AbstractBadgesByInNameQuery(String method) {
		super(method);
	}

	public Q setGetInName(String inName) {
		this.inName = inName;
		return (Q) this;
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "inName", getInName());
		return finalParameters;
	}
}
