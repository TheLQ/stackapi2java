/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.methods;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@Getter
public class VectorQueryMethod implements QueryMethod {
	protected final String raw;
	protected final Iterable<?> vector;

	public VectorQueryMethod(String raw, Iterable<?> vector) {
		Preconditions.checkArgument(raw.contains("{}"), "Raw method does not contain vector");
		this.raw = raw;
		this.vector = vector;
	}

	public String getFinal() {
		//Verify vector if it is required
		Iterator<?> vectorItr = vector.iterator();
		if (!vectorItr.hasNext())
			throw new RuntimeException("Vector cannot be empty");

		//Do the replace
		String methodFinal = StringUtils.replaceOnce(raw, "{}", QueryUtils.PARAMETER_JOINER.join(vectorItr));
		
		//Make sure we didn't miss anything
		if(methodFinal.contains("{}"))
			throw new RuntimeException("Still contains vector: " + methodFinal);
		
		return methodFinal;
	}
}
