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

import com.google.common.collect.ImmutableList;
import java.net.URI;
import lombok.Data;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Data
public class PostEntry implements ItemEntry {
	protected Integer postId;
	protected PostType postType;
	@MaybeAbsent
	protected ShallowUserEntry owner;
	protected String body;
	@MaybeAbsent
	protected ImmutableList<CommentEntry> comments;
	protected Integer downVoteCount;
	protected Integer upVoteCount;
	protected DateTime creationDate;
	protected DateTime lastActivityDate;
	@MaybeAbsent
	protected DateTime lastEditDate;
	protected URI link;
	protected Integer score;
}
