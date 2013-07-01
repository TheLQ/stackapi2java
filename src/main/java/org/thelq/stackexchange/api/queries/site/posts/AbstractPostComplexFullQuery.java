/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.posts;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.PostEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractComplexFullQuery;
import org.thelq.stackexchange.api.queries.site.SortableField;

/**
 *
 * @author Leon
 */
@Getter
public class AbstractPostComplexFullQuery<F extends Enum<F> & SortableField, Q extends AbstractPostComplexFullQuery<F, Q, I>, I extends ItemEntry> extends AbstractComplexFullQuery<F, Q, I> {
	protected final List<Integer> postIds = new ArrayList<Integer>();

	public AbstractPostComplexFullQuery(Class<F> enumClass, Class<I> itemClass, String method) {
		super(enumClass, itemClass, method);
	}

	public Q addPostId(int postId) {
		postIds.add(postId);
		return (Q) this;
	}

	@Override
	public String getMethod() {
		return QueryUtils.insertVector(method, postIds);
	}
}
