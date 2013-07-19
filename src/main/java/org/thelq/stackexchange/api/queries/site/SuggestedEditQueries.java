/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.Collection;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.SuggestedEditEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.SuggestedEditSort;

/**
 *
 * @author Leon
 */
public class SuggestedEditQueries {
	public static <Q extends AbstractComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>> Q all() {
		return new AbstractComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>(SuggestedEditEntry.class, new SimpleQueryMethod("suggested-edits"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>> Q byIds(@NonNull Collection<Integer> suggestedEditIds) {
		return new AbstractComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>(SuggestedEditEntry.class, new VectorQueryMethod("suggested-edits/{}", suggestedEditIds))
				.self();
	}
}
