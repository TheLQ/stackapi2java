/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.queries.site;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.SearchSort;

/**
 *
 * @author Leon
 */
@Getter
public class BaseSearchQuery<Q extends BaseSearchQuery<Q>> extends BaseComplexFullQuery<SearchSort<?>, Q, QuestionEntry> {
	protected final List<String> tagged = new ArrayList<String>();
	protected final List<String> notTagged = new ArrayList<String>();

	protected BaseSearchQuery(QueryMethod method) {
		super(QuestionEntry.class, method);
	}

	public Q addTagged(@NonNull String tag) {
		tagged.add(tag);
		return self();
	}

	public Q addNotTagged(@NonNull String notTag) {
		notTagged.add(notTag);
		return self();
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(!(!notTagged.isEmpty() && tagged.isEmpty()), "notTagged requires tagged to have at least one tag");
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		if (!tagged.isEmpty())
			finalParameters.put("tagged", QueryUtils.PARAMETER_JOINER.join(tagged));
		if (!notTagged.isEmpty())
			finalParameters.put("notTagged", QueryUtils.PARAMETER_JOINER.join(notTagged));
		return finalParameters;
	}

}
