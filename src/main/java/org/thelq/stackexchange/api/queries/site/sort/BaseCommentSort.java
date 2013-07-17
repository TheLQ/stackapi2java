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
public class BaseCommentSort<M> extends ResultSort<M, BaseCommentSort<M>> {
	public static BaseCommentSort<DateTime> creation() {
		return new BaseCommentSort<DateTime>("creation");
	}

	public static BaseCommentSort<Integer> votes() {
		return new BaseCommentSort<Integer>("votes");
	}

	public BaseCommentSort(String name) {
		super(name);
	}
}
