/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.queries.AuthRequiredQuery;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;

/**
 *
 * @author Leon
 */
public abstract class AbstractCommentWriteQuery<Q extends AbstractCommentWriteQuery<Q>> extends AbstractSiteQuery<Q, CommentEntry> implements AuthRequiredQuery {
	public AbstractCommentWriteQuery(QueryMethod method) {
		super(CommentEntry.class, method);
	}
}
