/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.queries;

/**
 *
 * @author Leon
 */
public interface PagableQuery<Q extends AbstractQuery<Q, ?>> {
	public Integer getPage();
	public Q setPage(int page);
	public Integer getPageSize();
	public Q setPageSize(int pageSize);
}
