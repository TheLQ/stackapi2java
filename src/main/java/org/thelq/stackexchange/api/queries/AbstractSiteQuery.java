/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractSiteQuery<Q extends AbstractSiteQuery<Q>> extends AbstractPagableQuery<Q> {
	protected String site;

	public AbstractSiteQuery(Class itemClass, String method, List<?>... vectors) {
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
