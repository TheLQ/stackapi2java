/**
 * Copyright (C) 2013 Leon Blakey <lord.quackstar at gmail.com>
 *
 * This file is part of stackapi2java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thelq.stackexchange.api.model.types;

import java.net.URI;
import lombok.Getter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
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
