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
public class CommentsSort<M> extends ResultSort<M, CommentsSort<M>> {
	public static CommentsSort<DateTime> creation() {
		return new CommentsSort<DateTime>("creation");
	}

	public static CommentsSort<Integer> votes() {
		return new CommentsSort<Integer>("votes");
	}

	public CommentsSort(String name) {
		super(name);
	}
}
