/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.badges;

/**
 *
 * @author Leon
 */
public class BadgesTagBasedQuery extends AbstractBadgesByInNameQuery<BadgesTagBasedQuery> {
	public BadgesTagBasedQuery() {
		super("badges/tags");
	}
}
