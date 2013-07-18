/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.BadgeEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.BaseBadgeSort;
import org.thelq.stackexchange.api.queries.site.sort.TypeBadgeSort;

/**
 *
 * @author Leon
 */
public class BadgeQueries {
	public static <Q extends AbstractComplexFullQuery<TypeBadgeSort<?>, Q, BadgeEntry>> Q all() {
		return new AbstractComplexFullQuery<TypeBadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<TypeBadgeSort<?>, Q, BadgeEntry>> Q all(@NonNull String inname) {
		return new AbstractComplexFullQuery<TypeBadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges"))
				.setParameter("inname", inname);
	}

	public static <Q extends AbstractComplexFullQuery<BaseBadgeSort<?>, Q, BadgeEntry>> Q allNamed() {
		return new AbstractComplexFullQuery<BaseBadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/name"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<BaseBadgeSort<?>, Q, BadgeEntry>> Q allNamed(@NonNull String inname) {
		return new AbstractComplexFullQuery<BaseBadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/name"))
				.setParameter("inname", inname);
	}

	public static <Q extends AbstractComplexFullQuery<BaseBadgeSort<?>, Q, BadgeEntry>> Q allTagBased() {
		return new AbstractComplexFullQuery<BaseBadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<BaseBadgeSort<?>, Q, BadgeEntry>> Q allTagBased(@NonNull String inname) {
		return new AbstractComplexFullQuery<BaseBadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.setParameter("inname", inname);
	}
}
