/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import com.google.common.base.Preconditions;
import java.util.Collection;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.RevisionEntry;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;

/**
 *
 * @author Leon
 */
public class RevisionQueries {
	public static <Q extends AbstractComplexDateQuery<Q, RevisionEntry>> Q byIds(@NonNull Collection<String> revisionGuids) {
		return new AbstractComplexDateQuery<Q, RevisionEntry>(RevisionEntry.class, new VectorQueryMethod("revisions/{}", revisionGuids))
				.self();
	}
}
