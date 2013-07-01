/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.comments;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.thelq.stackexchange.api.queries.site.AbstractCommentWriteQuery;

/**
 *
 * @author Leon
 */
@Getter
@Setter
public class CommentEditQuery extends AbstractCommentWriteQuery<CommentEditQuery> {
	protected String body;

	public CommentEditQuery(int commentId) {
		super("comments/" + commentId + "/edit");
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		Preconditions.checkState(StringUtils.isNotBlank(body), "Must specify body");
		LinkedHashMap<String, String> finalParameters = super.buildFinalParameters();
		finalParameters.put("body", body);
		return finalParameters;
	}
}
