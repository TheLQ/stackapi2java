/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.posts;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.SuggestedEditEntry;

/**
 *
 * @author Leon
 */
public class PostSuggestedEditsQuery extends AbstractPostComplexFullQuery<SuggestedEditSort, PostSuggestedEditsQuery, SuggestedEditEntry> {
	public PostSuggestedEditsQuery() {
		super(SuggestedEditSort.class, SuggestedEditEntry.class, "post/{}/suggested-edits");
	}
	
	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(!getPostIds().isEmpty(), "Must specify at least one id");
		return super.buildFinalParameters();
	}
}
