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
public class QuestionRelatedSort<M> extends ResultSort<M, QuestionRelatedSort<M>> {
	public static QuestionRelatedSort<DateTime> activity() {
		return new QuestionRelatedSort<DateTime>("activity");
	}

	public static QuestionRelatedSort<DateTime> creation() {
		return new QuestionRelatedSort<DateTime>("creation");
	}

	public static QuestionRelatedSort<Integer> votes() {
		return new QuestionRelatedSort<Integer>("votes");
	}
	
	public static QuestionRelatedSort<Void> rank() {
		return new QuestionRelatedSort<Void>("rank");
	}

	public QuestionRelatedSort(String name) {
		super(name);
	}
}
