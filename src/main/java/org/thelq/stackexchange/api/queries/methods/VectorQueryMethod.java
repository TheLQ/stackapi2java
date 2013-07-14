/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.methods;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@Getter
public class VectorQueryMethod implements QueryMethod {
	protected final String raw;
	protected final Iterable<?> vector;
	protected final boolean vectorRequired;

	public VectorQueryMethod(String raw, Iterable<?> vector) {
		this(raw, vector, false);
	}

	public VectorQueryMethod(String raw, Iterable<?> vector, boolean vectorRequired) {
		this.raw = raw;
		this.vector = vector;
		this.vectorRequired = vectorRequired;
	}

	public String getFinal() {
		return QueryUtils.insertVector(raw, vector);
	}
}
