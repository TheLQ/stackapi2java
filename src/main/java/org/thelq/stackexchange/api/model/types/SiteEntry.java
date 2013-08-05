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
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.ItemEntry;

/**
 * Represents a site in the Stack Exchange network.
 * @see https://api.stackexchange.com/docs/types/site
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
@Data
@Setter(AccessLevel.NONE)
public class SiteEntry implements ItemEntry {
	protected ImmutableList<String> aliases;
	protected String apiSiteParameter;
	protected String audience;
	protected DateTime closedBetaDate;
	protected URI faviconUrl;
	protected URI highResolutionIconUrl;
	protected URI iconUrl;
	protected DateTime launchDate;
	protected URI logoUrl;
	protected ImmutableList<MarkdownExtension> markdownExtensions;
	protected String name;
	protected String openBetaDate;
	protected ImmutableList<RelatedSiteEntry> relatedSites;
	protected URI siteUrl;
	protected StyleEntry styling;
	protected String twitterAccount;

	/**
	 * All current markdown extensions. This may be extended in the future, but it
	 * is an enum for convenience
	 */
	public static enum MarkdownExtension {
		MATHJAX,
		PRETTIFY,
		BALSAMIQ,
		JTAB
	}
	
	/**
	 * All site states
	 */
	public static enum State {
		NORMAL,
		CLOSED_BETA,
		OPEN_BETA,
		LINKED_META
	}

	/**
	 * All site types. This may be extended in the future, but is it an enum for 
	 * convenience
	 */
	public static enum Type {
		MAIN_SITE,
		META_SITE
	}
}
