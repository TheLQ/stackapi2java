/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.LinkedHashMap;
import java.util.List;
import lombok.Getter;
import org.thelq.stackexchange.api.model.GenericEntry;
import org.thelq.stackexchange.api.queries.AbstractQuery;
import org.thelq.stackexchange.api.queries.PagableQuery;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractSitePagableQuery<Q extends AbstractSitePagableQuery<Q, I>, I extends GenericEntry> extends AbstractSiteQuery<Q, I> implements PagableQuery<Q> {
	protected Integer page;
	protected Integer pageSize;
	public AbstractSitePagableQuery(Class<I> itemClass, String method, List<?>... vectors) {
		super(itemClass, method, vectors);
	}

	@Override
	public Q setPage(int page) {
		this.page = page;
		return (Q) this;
	}

	@Override
	public Q setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return (Q) this;
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		putIfNotNull(finalParameters, "page", page);
		putIfNotNull(finalParameters, "pageSize", pageSize);
		return finalParameters;
	}
	
	
}
