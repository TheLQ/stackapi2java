/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import java.util.LinkedHashMap;
import java.util.List;
import lombok.Getter;
import org.thelq.stackexchange.api.model.GenericEntry;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractPagableQuery<Q extends AbstractPagableQuery<Q, I>, I extends GenericEntry> extends AbstractQuery<Q, I> {
	protected Integer page;
	protected Integer pageSize;
	public AbstractPagableQuery(Class<I> itemClass, String method, List<?>... vectors) {
		super(itemClass, method, vectors);
	}

	public Q setPage(int page) {
		this.page = page;
		return (Q) this;
	}

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
