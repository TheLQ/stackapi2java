/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.posts;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.model.sort.CommentsSort;

/**
 *
 * @author Leon
 */
public class PostCommentsQuery extends AbstractPostComplexFullQuery<CommentsSort, PostCommentsQuery, CommentEntry> {
	public PostCommentsQuery() {
		super(CommentsSort.class, CommentEntry.class, "posts/{}/comments");

	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(!getPostIds().isEmpty(), "Must specify at least one id");
		return super.buildFinalParameters();
	}
}
