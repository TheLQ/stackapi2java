/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.queries.types;

import lombok.Getter;
import org.thelq.stackexchange.api.model.AnswerEntry;
import org.thelq.stackexchange.api.queries.AbstractComplexQuery;
import org.thelq.stackexchange.api.queries.types.AnswerQuery.SortField;
import org.thelq.stackexchange.api.queries.SortableField;

/**
 *
 * @author Leon
 */
@Getter
public class AnswerQuery extends AbstractComplexQuery<AnswerQuery, SortField> {
	public AnswerQuery() {
		super(AnswerEntry.class, SortField.class);
	}
	
	public static enum SortField implements SortableField {
		ACTIVITY,
		CREATION,
		VOTES
	}
}
