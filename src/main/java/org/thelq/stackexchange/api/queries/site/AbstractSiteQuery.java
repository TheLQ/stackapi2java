/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.AbstractQuery;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@Getter
public abstract class AbstractSiteQuery<Q extends AbstractSiteQuery<Q, I>, I extends ItemEntry> extends AbstractQuery<Q, I> {
	protected String site;

	public AbstractSiteQuery(Class<I> itemClass, String method) {
		super(itemClass, method);
	}

	public Q setSite(String site) {
		this.site = site;
		return self();
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(StringUtils.isNotBlank(getSite()), "Must specify site");
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "site", site);
		return finalParameters;
	}
}
