/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.search;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Leon
 */
public class SearchSimilarQuery extends AbstractSearchQuery<SearchSimilarQuery> {
	protected String title;

	public SearchSimilarQuery() {
		super("similar");
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(StringUtils.isNotBlank(title), "Must specify title");
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		finalParameters.put("title", title);
		return finalParameters;
	}
}
