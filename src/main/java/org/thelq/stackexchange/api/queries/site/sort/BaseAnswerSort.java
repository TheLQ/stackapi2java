/*
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
public class BaseAnswerSort<M> extends ResultSort<M, BaseAnswerSort<M>> {
	public static BaseAnswerSort<DateTime> activity() {
		return new BaseAnswerSort<DateTime>("activity");
	}

	public static BaseAnswerSort<DateTime> creation() {
		return new BaseAnswerSort<DateTime>("creation");
	}

	public static BaseAnswerSort<Integer> votes() {
		return new BaseAnswerSort<Integer>("votes");
	}

	public BaseAnswerSort(String name) {
		super(name);
	}
}
