/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.methods;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@RequiredArgsConstructor
@Getter
public class VectorQueryMethod implements QueryMethod {
	protected String raw;
	protected Iterable<?> iterator;

	public String getFinal() {
		return QueryUtils.insertVector(raw, iterator);
	}
}
