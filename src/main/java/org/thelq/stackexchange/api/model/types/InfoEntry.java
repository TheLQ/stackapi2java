/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model.types;

import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;

/**
 *
 * @author Leon
 */
@Getter
public class InfoEntry implements ItemEntry {
	protected Float answersPerMinute;
	protected Float badgesPerMinute;
	protected Float questionsPerMinute;
	protected String apiRevision;
	protected Integer newActiveUsers;
	protected SiteEntry site;
	protected Integer totalAccepted;
	protected Integer totalAnswers;
	protected Integer totalBadges;
	protected Integer totalQuestions;
	protected Integer totalUnanswered;
	protected Integer totalUsers;
	protected Integer totalVotes;
}
