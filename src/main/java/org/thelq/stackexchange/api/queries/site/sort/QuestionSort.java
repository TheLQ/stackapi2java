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
public class QuestionSort<M> extends ResultSort<M, QuestionSort<M>> {
	public static QuestionSort<DateTime> activity() {
		return new QuestionSort<DateTime>("activity");
	}

	public static QuestionSort<DateTime> creation() {
		return new QuestionSort<DateTime>("creation");
	}

	public static QuestionSort<Integer> votes() {
		return new QuestionSort<Integer>("votes");
	}

	public QuestionSort(String name) {
		super(name);
	}
}
