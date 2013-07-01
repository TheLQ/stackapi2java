/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.List;
import org.thelq.stackexchange.api.model.CommentEntry;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.queries.AuthRequiredQuery;

/**
 *
 * @author Leon
 */
public class AbstractCommentWriteQuery<Q extends AbstractCommentWriteQuery<Q>> extends AbstractSiteQuery<Q, CommentEntry> implements AuthRequiredQuery {
	public AbstractCommentWriteQuery(String method) {
		super(CommentEntry.class, method);
	}
}
