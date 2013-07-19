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
public class TagSynonymSort<M> extends ResultSort<M, TagSynonymSort<M>> {
	public static TagSynonymSort<DateTime> creation() {
		return new TagSynonymSort<DateTime>("creation");
	}

	public static TagSynonymSort<Integer> applied() {
		return new TagSynonymSort<Integer>("applied");
	}

	public static TagSynonymSort<DateTime> activity() {
		return new TagSynonymSort<DateTime>("activity");
	}

	public TagSynonymSort(String name) {
		super(name);
	}
}
