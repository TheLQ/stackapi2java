/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.comments;

import org.thelq.stackexchange.api.queries.site.AbstractCommentWriteQuery;

/**
 *
 * @author Leon
 */
public class CommentDeleteQuery extends AbstractCommentWriteQuery<CommentDeleteQuery> {
	public CommentDeleteQuery(int commentId) {
		super("comments/" + commentId + "/delete");
	}
}
