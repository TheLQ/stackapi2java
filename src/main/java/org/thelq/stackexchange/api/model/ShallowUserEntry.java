package org.thelq.stackexchange.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
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
		DOES_NOT_EXIST;

		@JsonValue
		public String jsonValue() {
			return name().toLowerCase();
		}
	}
}
