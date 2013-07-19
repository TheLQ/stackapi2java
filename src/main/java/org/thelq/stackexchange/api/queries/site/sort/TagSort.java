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
public class TagSort<M> extends ResultSort<M, TagSort<M>> {
	public static TagSort<Integer> popular() {
		return new TagSort<Integer>("popular");
	}

	public static TagSort<DateTime> activity() {
		return new TagSort<DateTime>("activity");
	}

	public static TagSort<String> name() {
		return new TagSort<String>("name");
	}

	public TagSort(String name) {
		super(name);
	}
}