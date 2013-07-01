/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.answer;

import org.thelq.stackexchange.api.model.types.AnswerEntry;

/**
 *
 * @author Leon
 */
public class AnswerQuery extends AbstractAnswerQuery<AnswerQuery, AnswerEntry> {
	public AnswerQuery() {
		super("answers/{}", AnswerEntry.class);
	}
}
