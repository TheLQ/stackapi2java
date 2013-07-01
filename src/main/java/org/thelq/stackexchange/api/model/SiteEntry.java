/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model;

import com.google.common.collect.ImmutableList;
import java.net.URI;
import lombok.Getter;
import org.joda.time.DateTime;

/**
 *
 * @author Leon
 */
@Getter
public class SiteEntry implements ItemEntry {
	@MaybeAbsent
	protected ImmutableList<String> aliases;
	protected String apiSiteParameter;
	protected String audience;
	@MaybeAbsent
	protected DateTime closedBetaDate;
	protected URI faviconUrl;
	@MaybeAbsent
	protected URI highResolutionIconUrl;
	protected URI iconUrl;
	protected DateTime launchDate;
	protected URI logoUrl;
	@MaybeAbsent
	protected ImmutableList<String> markdownExtensions;
	protected String name;
	@MaybeAbsent
	protected String openBetaDate;
	@MaybeAbsent
	protected ImmutableList<RelatedSiteEntry> relatedSites;
	protected URI siteUrl;
	protected StyleEntry styling;
	@MaybeAbsent
	protected String twitterAccount;

	public static enum State {
		NORMAL,
		CLOSED_BETA,
		OPEN_BETA,
		LINKED_META
	}

	public static enum Type {
		MAIN_SITE,
		META_SIZEs
	}
}
