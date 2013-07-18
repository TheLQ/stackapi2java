/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.Collection;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.types.BadgeEntry;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.BadgeSort;
import org.thelq.stackexchange.api.queries.site.sort.BadgeTypeSort;

/**
 *
 * @author Leon
 */
public class BadgeQueries {
	public static <Q extends AbstractComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>> Q all() {
		return new AbstractComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>> Q all(@NonNull String inname) {
		return new AbstractComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges"))
				.setParameter("inname", inname);
	}

	public static <Q extends AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q allNamed() {
		return new AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/name"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q allNamed(@NonNull String inname) {
		return new AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/name"))
				.setParameter("inname", inname);
	}

	public static <Q extends AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q allTagBased() {
		return new AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q allTagBased(@NonNull String inname) {
		return new AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.setParameter("inname", inname);
	}
	
	public static <Q extends AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q byIds(@NonNull Collection<Integer> badgeIds) {
		return new AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new VectorQueryMethod("badges/tags", badgeIds))
				.self();
	}
	
	public static <Q extends AbstractComplexDateQuery<Q, BadgeEntry>> Q recipients() {
		return new AbstractComplexDateQuery<Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.self();
	}
	
	public static <Q extends AbstractComplexDateQuery<Q, BadgeEntry>> Q recipientsByIds(@NonNull Collection<Integer> badgeIds) {
		return new AbstractComplexDateQuery<Q, BadgeEntry>(BadgeEntry.class, new VectorQueryMethod("badges/tags", badgeIds))
				.self();
	}
}
