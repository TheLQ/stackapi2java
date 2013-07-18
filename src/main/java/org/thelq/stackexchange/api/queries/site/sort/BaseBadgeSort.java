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
public class BaseBadgeSort<M> extends ResultSort<M, BaseBadgeSort<M>> {
	public static BaseBadgeSort<BadgeEntry.Rank> rank() {
		return new BaseBadgeSort<BadgeEntry.Rank>("rank");
	}

	public static BaseBadgeSort<String> name() {
		return new BaseBadgeSort<String>("name");
	}

	public BaseBadgeSort(String name) {
		super(name);
	}
}
