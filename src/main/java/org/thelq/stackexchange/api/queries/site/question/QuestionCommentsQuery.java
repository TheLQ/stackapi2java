/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.queries.site.question;

import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.sort.CommentsSort;
import org.thelq.stackexchange.api.model.types.CommentEntry;

/**
 *
 * @author Leon
 */
public class QuestionCommentsQuery extends AbstractQuestionByIdQuery<CommentsSort, QuestionCommentsQuery, CommentEntry> {

	public QuestionCommentsQuery() {
		super(CommentsSort.class, CommentEntry.class, "questions/{}/comments", true);
	}

}
