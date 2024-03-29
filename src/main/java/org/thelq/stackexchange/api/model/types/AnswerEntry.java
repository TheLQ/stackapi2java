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
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;

/**
 * Represents an answer to a question on one of the Stack Exchange sites.
 * @see https://api.stackexchange.com/docs/types/answer
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Data
@Setter(AccessLevel.NONE)
public class AnswerEntry implements ItemEntry {
	protected Integer answerId;
	protected Integer questionId;
	protected String title;
	protected String body;
	protected ImmutableList<CommentEntry> comments;
	protected DateTime communityOwnedDate;
	protected DateTime creationDate;
	protected DateTime lastActivityDate;
	protected DateTime lastEditDate;
	protected DateTime lockedDate;
	protected Integer score;
	protected Integer downVoteCount;
	protected Integer upVoteCount;
	@JsonProperty("is_accepted")
	protected Boolean accepted;
	protected URI link;
	protected ShallowUserEntry owner;
	protected ImmutableList<String> tags;
}
