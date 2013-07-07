/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.search;

import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.Setter;
import org.thelq.stackexchange.api.queries.QueryUtils;

/**
 *
 * @author Leon
 */
@Getter
@Setter
public class SearchAdvancedQuery extends AbstractSearchQuery<SearchAdvancedQuery> {
	protected String q;
	protected Boolean accepted;
	protected Boolean answers;
	protected String body;
	protected Boolean closed;
	protected Boolean migrated;
	protected Boolean notice;
	protected String title;
	protected Integer user;
	protected String url;
	protected Integer views;
	protected Boolean wiki;

	public SearchAdvancedQuery() {
		super("search/advanced");
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		QueryUtils.putIfNotNull(finalParameters, "q", q);
		QueryUtils.putIfNotNull(finalParameters, "accepted", accepted);
		QueryUtils.putIfNotNull(finalParameters, "answers", answers);
		QueryUtils.putIfNotNull(finalParameters, "body", body);
		QueryUtils.putIfNotNull(finalParameters, "closed", closed);
		QueryUtils.putIfNotNull(finalParameters, "migrated", migrated);
		QueryUtils.putIfNotNull(finalParameters, "notice", notice);
		QueryUtils.putIfNotNull(finalParameters, "title", title);
		QueryUtils.putIfNotNull(finalParameters, "user", user);
		QueryUtils.putIfNotNull(finalParameters, "url", url);
		QueryUtils.putIfNotNull(finalParameters, "views", views);
		QueryUtils.putIfNotNull(finalParameters, "wiki", wiki);
		return finalParameters;
	}
}
