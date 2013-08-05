/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.queries.site;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;

/**
 *
 * @author Leon
 */
@Getter
@Setter
@Accessors(chain = true)
public class SearchBasicQuery extends BaseSearchQuery<SearchBasicQuery> {
	protected String inTitle;

	public SearchBasicQuery() {
		super(new SimpleQueryMethod("search"));
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(StringUtils.isNotBlank(inTitle) || !tagged.isEmpty(), "Must specify tag or intitle");
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "inTitle", inTitle);
		return finalParameters;
	}

}
