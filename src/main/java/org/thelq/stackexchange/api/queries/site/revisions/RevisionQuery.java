/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.revisions;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.RevisionEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractComplexDateQuery;

/**
 *
 * @author Leon
 */
public class RevisionQuery extends AbstractComplexDateQuery<RevisionQuery, RevisionEntry> {
	protected final List<String> revisionGuids = new ArrayList<String>();

	public RevisionQuery() {
		super(RevisionEntry.class, "revisions/{}");
	}

	public RevisionQuery addRevisionGuid(@NonNull String revisionGuid) {
		revisionGuids.add(revisionGuid);
		return this;
	}

	@Override
	public String getMethod() {
		Preconditions.checkState(!revisionGuids.isEmpty(), "Must specify at least one revision guid");
		return QueryUtils.insertVector(super.getMethod(), revisionGuids);
	}
}
