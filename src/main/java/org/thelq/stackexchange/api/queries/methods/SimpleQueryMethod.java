/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.methods;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Leon
 */
@RequiredArgsConstructor
@Getter
public class SimpleQueryMethod implements QueryMethod {
	protected final String raw;

	public String getFinal() {
		return raw;
	}
}
