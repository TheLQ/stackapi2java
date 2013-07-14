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
	protected final boolean vectorRequired;

	public VectorQueryMethod(String raw, Iterable<?> vector) {
		this(raw, vector, false);
	}

	public VectorQueryMethod(String raw, Iterable<?> vector, boolean vectorRequired) {
		Preconditions.checkArgument(raw.contains("{}"), "Raw method does not contain vector");
		this.raw = raw;
		this.vector = vector;
		this.vectorRequired = vectorRequired;
	}

	public String getFinal() {
		//Verify vector if it is required
		Iterator<?> vectorItr = vector.iterator();
		boolean containsValues = vectorItr.hasNext();
		if (vectorRequired && !containsValues)
			throw new RuntimeException("Vector cannot be empty");

		//Do the replace
		String methodFinal;
		if (!containsValues)
			//Just return the method minus the {}
			methodFinal = StringUtils.remove(raw, "{}");
		else
			methodFinal = StringUtils.replaceOnce(raw, "{}", QueryUtils.PARAMETER_JOINER.join(vectorItr));
		
		//Make sure we didn't miss anything
		if(methodFinal.contains("{}"))
			throw new RuntimeException("Still contains vector: " + methodFinal);
		
		return methodFinal;
	}
}
