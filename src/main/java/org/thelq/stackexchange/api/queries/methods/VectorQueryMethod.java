/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.methods;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@Getter
public class VectorQueryMethod implements QueryMethod {
	protected final String raw;
	protected final String vectorSingle;
	protected final Collection<?> vectorCollection;

	public VectorQueryMethod(@NonNull String raw, @NonNull String vectorSingle) {
		Preconditions.checkArgument(StringUtils.countMatches(raw, "{}") == 1, "Raw method does not contain vector");
		this.raw = raw;
		this.vectorSingle = vectorSingle;
		this.vectorCollection = null;
	}

	public VectorQueryMethod(@NonNull String raw, @NonNull Collection<?> vectorCollection) {
		Preconditions.checkArgument(StringUtils.countMatches(raw, "{}") == 1, "Raw method does not contain vector");
		Preconditions.checkArgument(!vectorCollection.isEmpty(), "Vector collection is empty");
		Preconditions.checkArgument(vectorCollection.size() < 100, "Vectors do not support more than 100 elements");
		this.raw = raw;
		this.vectorSingle = null;
		this.vectorCollection = vectorCollection;
	}

	public String getFinal() {
		String methodFinal = raw;
		if (vectorSingle != null)
			methodFinal = StringUtils.replaceOnce(raw, "{}", vectorSingle);
		else if (vectorCollection != null) {
			if (StringUtils.countMatches(raw, "{}") != 1)
				throw new RuntimeException("No more vectors to replace! Raw: " + raw + " | Final: " + methodFinal);
			//Verify vector if it is required
			Iterator<?> vectorItr = vectorCollection.iterator();
			if (!vectorItr.hasNext())
				throw new RuntimeException("Vector cannot be empty");

			//Do the replace
			methodFinal = StringUtils.replaceOnce(raw, "{}", QueryUtils.PARAMETER_JOINER.join(vectorItr));

			//Make sure we didn't miss anything
			if (methodFinal.contains("{}"))
				throw new RuntimeException("Still contains vector: " + methodFinal);
		} else
			throw new RuntimeException("No vector to replace! " + raw);
		return methodFinal;
	}
}
