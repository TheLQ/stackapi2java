/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model.types;

import java.net.URI;
import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Getter
public class UserEntry implements ItemEntry {
	@MaybeAbsent
	protected String aboutMe;
	@MaybeAbsent
	protected Integer acceptRate;
	protected Integer accountId;
	@MaybeAbsent
	protected Integer age;
	protected Integer answerCount;
	protected BadgeCountEntry badgeCounts;
	protected DateTime creationDate;
	protected String displayName;
	protected Integer downVoteCount;
	protected Boolean isEmployee;
	protected DateTime lastAccessDate;
	@MaybeAbsent
	protected DateTime lastModifiedDate;
	protected URI link;
	@MaybeAbsent
	protected String location;
	protected URI profileImage;
	protected Integer questionCount;
	protected Integer reputation;
	protected Integer reputationChangeDay;
	protected Integer reputationChangeMonth;
	protected Integer reputationChangeQuarter;
	protected Integer reputationChangeWeek;
	protected Integer reputationChangeYear;
	@MaybeAbsent
	protected DateTime timedPenaltyDate;
	protected Integer upVoteCount;
	protected Integer userId;
	protected UserType userType;
	protected Integer viewCount;
	@MaybeAbsent
	protected URI websiteUrl;
}
