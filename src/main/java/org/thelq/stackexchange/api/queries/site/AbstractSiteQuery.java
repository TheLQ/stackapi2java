/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.model.GenericEntry;
import org.thelq.stackexchange.api.queries.AbstractQuery;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractSiteQuery<Q extends AbstractSiteQuery<Q, I>, I extends GenericEntry> extends AbstractQuery<Q, I> {
	protected String site;

	public AbstractSiteQuery(Class<I> itemClass, String method, List<?>... vectors) {
		super(itemClass, method, vectors);
	}

	public Q setSite(String site) {
		this.site = site;
		return (Q) this;
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(StringUtils.isNotBlank(getSite()), "Must specify site");
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		putIfNotNull(finalParameters, "site", site);
		return finalParameters;
	}
}
