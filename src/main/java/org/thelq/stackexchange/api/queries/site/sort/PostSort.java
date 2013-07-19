/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.sort;

import org.joda.time.DateTime;

/**
 *
 * @author Leon
 */
public class PostSort<M> extends ResultSort<M, PostSort<M>> {
	public static PostSort<DateTime> activity() {
		return new PostSort<DateTime>("activity");
	}

	public static PostSort<DateTime> creation() {
		return new PostSort<DateTime>("creation");
	}

	public static PostSort<Integer> votes() {
		return new PostSort<Integer>("votes");
	}

	public PostSort(String name) {
		super(name);
	}
}
