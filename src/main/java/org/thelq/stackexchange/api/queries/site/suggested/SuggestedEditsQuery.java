/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.suggested;

import java.util.ArrayList;
import java.util.List;
import org.thelq.stackexchange.api.model.sort.SuggestedEditSort;
import org.thelq.stackexchange.api.model.types.SuggestedEditEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractComplexFullQuery;

/**
 *
 * @author Leon
 */
public class SuggestedEditsQuery extends AbstractComplexFullQuery<SuggestedEditSort, SuggestedEditsQuery, SuggestedEditEntry> {
	protected final List<Integer> suggestedEditIds = new ArrayList<Integer>();

	public SuggestedEditsQuery() {
		super(SuggestedEditSort.class, SuggestedEditEntry.class, "suggested-edits/{}");
	}

	public SuggestedEditsQuery addSuggestedEditId(int suggestedEditId) {
		suggestedEditIds.add(suggestedEditId);
		return this;
	}

	@Override
	public String getMethod() {
		return QueryUtils.insertVector(method, suggestedEditIds);
	}
}
