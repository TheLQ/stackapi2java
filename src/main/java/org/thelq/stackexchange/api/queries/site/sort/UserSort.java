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
public class UserSort<M> extends ResultSort<M, UserSort<M>> {
	public static UserSort<Integer> reputation() {
		return new UserSort<Integer>("reputation");
	}

	public static UserSort<DateTime> creation() {
		return new UserSort<DateTime>("creation");
	}

	public static UserSort<String> name() {
		return new UserSort<String>("name");
	}

	public static UserSort<DateTime> modified() {
		return new UserSort<DateTime>("modified");
	}

	public UserSort(String name) {
		super(name);
	}
}
