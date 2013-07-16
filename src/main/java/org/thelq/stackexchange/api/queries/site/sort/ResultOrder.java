/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 * @author Leon
 */
@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public enum ResultOrder {
	ASCENDING("asc"),
	DESCENDING("desc");
	
	protected final String jsonValue;
}
