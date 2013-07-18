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
public class QuestionTaggedSort<M> extends ResultSort<M, QuestionTaggedSort<M>> {
	public static QuestionTaggedSort<DateTime> activity() {
		return new QuestionTaggedSort<DateTime>("activity");
	}

	public static QuestionTaggedSort<DateTime> creation() {
		return new QuestionTaggedSort<DateTime>("creation");
	}

	public static QuestionTaggedSort<Integer> votes() {
		return new QuestionTaggedSort<Integer>("votes");
	}

	public static QuestionTaggedSort<Void> hot() {
		return new QuestionTaggedSort<Void>("hot");
	}

	public static QuestionTaggedSort<Void> week() {
		return new QuestionTaggedSort<Void>("week");
	}

	public static QuestionTaggedSort<Void> month() {
		return new QuestionTaggedSort<Void>("month");
	}

	public QuestionTaggedSort(String name) {
		super(name);
	}
}
