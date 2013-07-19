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
class AbstractCommentWriteQuery<Q extends AbstractSiteQuery<Q, CommentEntry>> extends AbstractSiteQuery<Q, CommentEntry> implements AuthRequiredQuery {
	public AbstractCommentWriteQuery(Class<CommentEntry> itemClass, QueryMethod method) {
		super(itemClass, method);
	}
}
