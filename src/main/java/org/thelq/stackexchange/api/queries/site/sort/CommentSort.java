/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.sort;

import org.joda.time.DateTime;
import org.thelq.stackexchange.api.queries.site.sort.ResultSort;

/**
 *
 * @author Leon
 */
public class CommentSort<M> extends ResultSort<M, CommentSort<M>> {
	public static CommentSort<DateTime> creation() {
		return new CommentSort<DateTime>("creation");
	}

	public static CommentSort<Integer> votes() {
		return new CommentSort<Integer>("votes");
	}

	public CommentSort(String name) {
		super(name);
	}
}
