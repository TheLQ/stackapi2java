/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.LinkedHashMap;
import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.PagableQuery;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractSitePagableQuery<Q extends AbstractSitePagableQuery<Q, I>, I extends ItemEntry> extends AbstractSiteQuery<Q, I> implements PagableQuery<Q> {
	protected Integer page;
	protected Integer pageSize;
	public AbstractSitePagableQuery(Class<I> itemClass, QueryMethod method) {
		super(itemClass, method);
	}

	@Override
	public Q setPage(int page) {
		this.page = page;
		return self();
	}

	@Override
	public Q setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return self();
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "page", page);
		QueryUtils.putIfNotNull(finalParameters, "pageSize", pageSize);
		return finalParameters;
	}
	
	
}
