/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.types;

import org.thelq.stackexchange.api.model.AnswerEntry;

/**
 *
 * @author Leon
 */
public class AnswerQuery extends AbstractAnswerQuery<AnswerQuery, AnswerEntry> {
	public AnswerQuery() {
		super("answers/{}", AnswerEntry.class);
	}
}
