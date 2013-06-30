/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import lombok.Getter;

/**
 *
 * @author Leon
 */
public class AbstractPagableQuery<Q extends AbstractPagableQuery<Q>> extends AbstractQuery<Q> {
	public AbstractPagableQuery(Class itemClass) {
		super(itemClass);
	}

	public Integer getPage() {
		return Integer.valueOf(getParameters().get("page"));
	}

	public Q setPage(int page) {
		setParameter("page", String.valueOf(page));
		return (Q) this;
	}

	public Integer getPageSize() {
		return Integer.valueOf(getParameters().get("pagesize"));
	}

	public Q setPageSize(int pageSize) {
		setParameter("pageSize", String.valueOf(pageSize));
		return (Q) this;
	}
}
