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
		Preconditions.checkArgument(revisionGuids.size() > 20, "Only 20 revision guids are supported by the StackExchange API, given %s", revisionGuids.size());
		return new AbstractComplexDateQuery<Q, RevisionEntry>(RevisionEntry.class, new VectorQueryMethod("revisions/{}", revisionGuids))
				.self();
	}
}
