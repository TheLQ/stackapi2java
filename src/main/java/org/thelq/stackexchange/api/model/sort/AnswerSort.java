/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model.sort;

import org.joda.time.DateTime;
import org.thelq.stackexchange.api.queries.site.sort.ResultSort;

/**
 *
 * @author Leon
 */
public class AnswerSort<M> extends ResultSort<M, AnswerSort<M>> {
	public static AnswerSort<DateTime> activity() {
		return new AnswerSort<DateTime>("activity");
	}

	public static AnswerSort<DateTime> creation() {
		return new AnswerSort<DateTime>("creation");
	}

	public static AnswerSort<Integer> votes() {
		return new AnswerSort<Integer>("votes");
	}

	public AnswerSort(String name) {
		super(name);
	}
}
