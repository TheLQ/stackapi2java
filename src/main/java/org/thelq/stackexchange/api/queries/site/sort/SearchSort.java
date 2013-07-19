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
public class SearchSort<M> extends ResultSort<M, SearchSort<M>> {
	public static SearchSort<DateTime> activity() {
		return new SearchSort<DateTime>("activity");
	}

	public static SearchSort<DateTime> creation() {
		return new SearchSort<DateTime>("creation");
	}

	public static SearchSort<Integer> votes() {
		return new SearchSort<Integer>("votes");
	}

	public static SearchSort<Void> relevance() {
		return new SearchSort<Void>("relevance");
	}

	public SearchSort(String name) {
		super(name);
	}
}
