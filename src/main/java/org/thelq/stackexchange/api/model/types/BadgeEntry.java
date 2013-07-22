/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model.types;

import java.net.URI;
import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Getter
public class BadgeEntry implements ItemEntry {
	protected Integer badgeId;
	protected Integer awardCount;
	protected Type badgeType;
	protected String description;
	protected URI link;
	protected String name;
	protected Rank rank;
	@MaybeAbsent
	protected ShallowUserEntry user;

	public static enum Type {
		NAMED,
		TAG_BASED;

		@Override
		public String toString() {
			//TODO: Less hacky way?
			return name().toLowerCase();
		}
	}

	public static enum Rank {
		GOLD,
		SILVER,
		BRONZE;

		@Override
		public String toString() {
			//TODO: Less hacky way?
			return name().toLowerCase();
		}
	}
}
