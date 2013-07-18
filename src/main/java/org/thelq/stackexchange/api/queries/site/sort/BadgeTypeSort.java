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
public class BadgeTypeSort<M> extends ResultSort<M, BadgeTypeSort<M>> {
	public static BadgeTypeSort<BadgeEntry.Rank> rank() {
		return new BadgeTypeSort<BadgeEntry.Rank>("rank");
	}

	public static BadgeTypeSort<String> name() {
		return new BadgeTypeSort<String>("name");
	}

	public static BadgeTypeSort<BadgeEntry.Type> type() {
		return new BadgeTypeSort<BadgeEntry.Type>("type");
	}

	public BadgeTypeSort(String name) {
		super(name);
	}
}
