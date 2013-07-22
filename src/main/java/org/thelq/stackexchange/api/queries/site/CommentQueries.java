/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.Collection;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.CommentSort;

/**
 *
 * @author Leon
 */
public class CommentQueries {
	public static <Q extends AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q all() {
		return new AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new SimpleQueryMethod("comments"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q byIds(@NonNull Collection<Integer> commentIds) {
		return new AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("comments/{}", commentIds))
				.self();
	}

	public static <Q extends AbstractSiteQuery<Q, CommentEntry>> Q edit(int commentId, @NonNull String body) {
		return new AbstractSiteQuery<Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("comments/{}/edit", String.valueOf(commentId)))
				.setParameter("body", body)
				.setAuthRequired(true);
	}

	public static <Q extends AbstractSiteQuery<Q, CommentEntry>> Q delete(int commentId) {
		return new AbstractSiteQuery<Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("comments/{}/delete", String.valueOf(commentId)))
				.setAuthRequired(true);
	}
}
