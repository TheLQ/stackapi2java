/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.posts;

import java.util.ArrayList;
import java.util.List;
import org.thelq.stackexchange.api.model.types.RevisionEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractComplexDateQuery;

/**
 *
 * @author Leon
 */
//TODO: Is it possible to put this under AbstractPostComplexFullQuery so we don't copy and paste postIds?
public class PostRevisionsQuery extends AbstractComplexDateQuery<PostRevisionsQuery, RevisionEntry> {
	protected final List<Integer> postIds = new ArrayList<Integer>();

	public PostRevisionsQuery() {
		super(RevisionEntry.class, "posts/{}/revisions");
	}

	public PostRevisionsQuery addPostId(int postId) {
		postIds.add(postId);
		return this;
	}

	@Override
	public String getMethod() {
		return QueryUtils.insertVector(method, postIds);
	}
}
