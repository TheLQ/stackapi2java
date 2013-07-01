/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.answer;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.CommentEntry;

/**
 *
 * @author Leon
 */
public class AnswerCommentsQuery extends AbstractAnswerQuery<AnswerCommentsQuery, CommentEntry> {
	public AnswerCommentsQuery() {
		super("answers/{}/comments", CommentEntry.class);
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(!getAnswerIds().isEmpty(), "Must specify at least one answer id");
		return super.buildFinalParameters();
	}
}
