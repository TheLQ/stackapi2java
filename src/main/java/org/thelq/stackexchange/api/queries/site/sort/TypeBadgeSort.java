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
public class TypeBadgeSort<M> extends ResultSort<M, TypeBadgeSort<M>> {
	public static TypeBadgeSort<BadgeEntry.Rank> rank() {
		return new TypeBadgeSort<BadgeEntry.Rank>("rank");
	}

	public static TypeBadgeSort<String> name() {
		return new TypeBadgeSort<String>("name");
	}

	public static TypeBadgeSort<BadgeEntry.Type> type() {
		return new TypeBadgeSort<BadgeEntry.Type>("type");
	}

	public TypeBadgeSort(String name) {
		super(name);
	}
}
