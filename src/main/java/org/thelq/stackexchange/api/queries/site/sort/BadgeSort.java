/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.sort;

import org.thelq.stackexchange.api.model.types.BadgeEntry;

/**
 *
 * @author Leon
 */
public class BadgeSort<M> extends ResultSort<M, BadgeSort<M>> {
	public static BadgeSort<BadgeEntry.Rank> rank() {
		return new BadgeSort<BadgeEntry.Rank>("rank");
	}

	public static BadgeSort<String> name() {
		return new BadgeSort<String>("name");
	}

	public BadgeSort(String name) {
		super(name);
	}
}
