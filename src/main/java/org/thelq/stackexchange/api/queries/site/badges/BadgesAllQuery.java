/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.badges;

/**
 *
 * @author Leon
 */
public class BadgesAllQuery extends AbstractBadgesByInNameQuery<BadgesAllQuery> {
	public BadgesAllQuery() {
		super("badges");
	}
}
