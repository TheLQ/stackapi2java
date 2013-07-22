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
public class QuestionFavoriteSort<M> extends ResultSort<M, QuestionFavoriteSort<M>> {
	public static QuestionFavoriteSort<DateTime> activity() {
		return new QuestionFavoriteSort<DateTime>("activity");
	}

	public static QuestionFavoriteSort<DateTime> creation() {
		return new QuestionFavoriteSort<DateTime>("creation");
	}

	public static QuestionFavoriteSort<Integer> votes() {
		return new QuestionFavoriteSort<Integer>("votes");
	}

	public static QuestionFavoriteSort<DateTime> added() {
		return new QuestionFavoriteSort<DateTime>("added");
	}

	public QuestionFavoriteSort(String name) {
		super(name);
	}
}
