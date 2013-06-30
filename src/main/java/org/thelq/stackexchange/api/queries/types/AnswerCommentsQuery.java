/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.types;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;

/**
 *
 * @author Leon
 */
public class AnswerCommentsQuery extends AnswerQuery<AnswerCommentsQuery> {
	public AnswerCommentsQuery() {
		super("answers/{}/comments");
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(!getAnswerIds().isEmpty(), "Must specify at least one answer id");
		return super.buildFinalParameters();
	}
}