package org.thelq.stackexchange.api.model;

import lombok.Data;

/**
 *
 * @author Leon
 */
@Data
public class ShallowUserEntry {
	@MaybeAbsent
	protected int userId;
	protected Type userType;
	@MaybeAbsent
	protected String displayName;
	@MaybeAbsent
	protected int acceptRate;
	@MaybeAbsent
	protected String link;
	@MaybeAbsent
	protected String profileImage;
	@MaybeAbsent
	protected int reputation;

	public static enum Type {
		UNREGISTERED,
		REGISTERED,
		MODERATOR,
		DOESNOTEXIST;

		@Override
		public String toString() {
			//TODO: There has got to be a better way to get uppercase enums working
			return name().toLowerCase();
		}
	}
}
