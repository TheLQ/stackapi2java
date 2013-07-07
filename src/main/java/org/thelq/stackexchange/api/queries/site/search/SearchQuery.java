/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.search;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@Getter
@Setter
public class SearchQuery extends AbstractSearchQuery<SearchQuery> {
	protected String inTitle;

	public SearchQuery() {
		super("search");
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(StringUtils.isNotBlank(inTitle) || !tagged.isEmpty(), "Must specify tag or intitle");
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "inTitle", inTitle);
		return finalParameters;
	}
}
