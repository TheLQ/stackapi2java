/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.comments;

import java.util.ArrayList;
import java.util.List;
import org.thelq.stackexchange.api.model.CommentEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractComplexFullQuery;

/**
 *
 * @author Leon
 */
public class CommentsQuery extends AbstractComplexFullQuery<CommentsSort, CommentsQuery, CommentEntry> {
	protected final List<Integer> commentIds;

	public CommentsQuery() {
		super(CommentsSort.class, CommentEntry.class, "comments/{}");
		this.commentIds = new ArrayList<Integer>();
	}

	public CommentsQuery addCommentId(int commentId) {
		commentIds.add(commentId);
		return this;
	}

	@Override
	public String getMethod() {
		return QueryUtils.insertVector(method, commentIds);
	}
}
