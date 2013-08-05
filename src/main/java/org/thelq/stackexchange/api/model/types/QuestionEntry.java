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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
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
public class QuestionEntry implements ItemEntry {
	@MaybeAbsent
	protected Integer acceptedAnswerId;
	protected Integer answerCount;
	@MaybeAbsent
	protected ImmutableList<AnswerEntry> answers;
	protected String body;
	@MaybeAbsent
	protected Integer bountyAmount;
	@MaybeAbsent
	protected DateTime BountyClosesDate;
	protected Integer closeVoteCount;
	@MaybeAbsent
	protected DateTime closedDate;
	@MaybeAbsent
	protected String closedReason;
	@MaybeAbsent
	protected ImmutableList<CommentEntry> comments;
	@MaybeAbsent
	protected DateTime communityOwnedDate;
	protected DateTime creationDate;
	protected Integer deleteVoteCount;
	protected Integer downVoteCount;
	protected Integer favoriteCount;
	@JsonProperty("is_answered")
	protected Boolean answered;
	protected DateTime lastActivityDate;
	@MaybeAbsent
	protected DateTime lastEditDate;
	protected URI link;
	@MaybeAbsent
	protected DateTime lockDate;
	@MaybeAbsent
	protected MigrationInfoEntry migrationInfo;
	@MaybeAbsent
	protected MigrationInfoEntry migrationTo;
	protected NoticeEntry notice;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	@MaybeAbsent
	protected DateTime protectedDate;
	protected Integer questionId;
	protected Integer reopenVoteCount;
	protected Integer score;
	protected ImmutableList<String> tags;
	protected String title;
	protected Integer upVoteCount;
	protected Integer viewCount;
}
