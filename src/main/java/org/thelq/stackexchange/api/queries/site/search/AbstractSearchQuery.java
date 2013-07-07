/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.search;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.sort.SearchSort;
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractComplexFullQuery;

/**
 *
 * @author Leon
 */
@Getter
public abstract class AbstractSearchQuery<Q extends AbstractSearchQuery<Q>> extends AbstractComplexFullQuery<SearchSort, Q, QuestionEntry> {
	protected final List<String> tagged = new ArrayList<String>();
	protected final List<String> notTagged = new ArrayList<String>();

	public AbstractSearchQuery(String method) {
		super(SearchSort.class, QuestionEntry.class, method);
	}

	public Q addTagged(@NonNull String tag) {
		tagged.add(tag);
		return (Q) this;
	}

	public Q addNotTagged(@NonNull String notTag) {
		notTagged.add(notTag);
		return (Q) this;
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		if (!tagged.isEmpty())
			finalParameters.put("tagged", QueryUtils.PARAMETER_JOINER.join(tagged));
		if (!notTagged.isEmpty())
			finalParameters.put("notTagged", QueryUtils.PARAMETER_JOINER.join(notTagged));
		return finalParameters;
	}
}
