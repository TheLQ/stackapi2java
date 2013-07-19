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
public class SuggestedEditSort<M> extends ResultSort<M, SuggestedEditSort<M>> {
	public static SuggestedEditSort<DateTime> creation() {
		return new SuggestedEditSort<DateTime>("creation");
	}

	public static SuggestedEditSort<DateTime> approval() {
		return new SuggestedEditSort<DateTime>("approval");
	}

	public static SuggestedEditSort<DateTime> rejection() {
		return new SuggestedEditSort<DateTime>("rejection");
	}

	public SuggestedEditSort(String name) {
		super(name);
	}
}
