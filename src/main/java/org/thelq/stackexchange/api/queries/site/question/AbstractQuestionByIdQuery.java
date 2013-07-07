/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.question;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;
import org.thelq.stackexchange.api.model.SortableField;
import org.thelq.stackexchange.api.model.sort.AnswerSort;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.site.AbstractComplexFullQuery;

/**
 *
 * @author Leon
 */
@Getter
public abstract class AbstractQuestionByIdQuery<F extends Enum<F> & SortableField, Q extends AbstractQuestionByIdQuery<F, Q, I>, I extends ItemEntry> extends AbstractComplexFullQuery<F, Q, I> {
	protected final List<Integer> questionIds = new ArrayList<Integer>();
	protected final boolean idsRequired;

	public AbstractQuestionByIdQuery(Class<F> enumClass, Class<I> itemClass, String method, boolean idsRequired) {
		super(enumClass, itemClass, method);
		this.idsRequired = idsRequired;
	}

	public Q addQuestionId(int questionId) {
		questionIds.add(questionId);
		return (Q) this;
	}

	@Override
	public String getMethod() {
		if(idsRequired)
			Preconditions.checkState(!getQuestionIds().isEmpty(), "Must specify at least one id");
		return QueryUtils.insertVector(super.getMethod(), questionIds);
	}
}
