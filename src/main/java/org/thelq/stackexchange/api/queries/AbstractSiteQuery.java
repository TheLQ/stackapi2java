/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Leon
 */
public class AbstractSiteQuery<Q extends AbstractSiteQuery<Q>> extends AbstractPagableQuery<Q> {
	public AbstractSiteQuery(Class itemClass) {
		super(itemClass);
	}

	public String getSite() {
		return getParameters().get("site");
	}

	public Q setSite(String site) {
		setParameter("site", site);
		return (Q) this;
	}

	@Override
	public void validate() throws IllegalStateException {
		super.validate();
		Preconditions.checkState(StringUtils.isNotBlank(getSite()), "Must specify site");
	}
}
