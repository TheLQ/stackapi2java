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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Getter
@ToString
@EqualsAndHashCode
public class AnswerEntry implements ItemEntry {
	protected Integer answerId;
	protected Integer questionId;
	protected String title;
	protected String body;
	@MaybeAbsent
	protected ImmutableList<CommentEntry> comments;
	@MaybeAbsent
	protected DateTime communityOwnedDate;
	protected DateTime creationDate;
	protected DateTime lastActivityDate;
	@MaybeAbsent
	protected DateTime lastEditDate;
	protected DateTime lockDate;
	protected Integer score;
	protected Integer downVoteCount;
	protected Integer upVoteCount;
	@JsonProperty("is_accepted")
	protected Boolean accepted;
	protected String link;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	protected ImmutableList<String> tags;
	
}
