package org.thelq.stackexchange.api.model.types;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Data
public class ShallowUserEntry {
	@MaybeAbsent
	protected Integer userId;
	protected UserType userType;
	@MaybeAbsent
	protected String displayName;
	@MaybeAbsent
	protected Integer acceptRate;
	@MaybeAbsent
	protected String link;
	@MaybeAbsent
	protected String profileImage;
	@MaybeAbsent
	protected Integer reputation;
}
