/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.question;

import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.NonNull;
import org.thelq.stackexchange.api.model.SortableField;
import org.thelq.stackexchange.api.model.sort.QuestionByTagSort;
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.queries.site.AbstractComplexFullQuery;

/**
 *
 * @author Leon
 */
public class AbstractQuestionByTagQuery<F extends Enum<F> & SortableField, Q extends AbstractQuestionByTagQuery<F, Q>> extends AbstractComplexFullQuery<F, Q, QuestionEntry> {
	/**
	 * Joiner to create parameter for query. Used instead of StringUtils.join since this
	 * throws NPE's on null elements
	 */
	protected static final Joiner TAGGED_JOINER = Joiner.on(';');
	protected final List<String> tagged = new ArrayList<String>();

	public AbstractQuestionByTagQuery(Class<F> enumClass, String method) {
		super(enumClass, QuestionEntry.class, method);
	}

	protected Q addTag(@NonNull String tag) {
		tagged.add(tag);
		return (Q) this;
	}

	@Override
	public LinkedHashMap<String, String> buildFinalParameters() throws IllegalStateException {
		LinkedHashMap<String, String> finalParameters = new LinkedHashMap<String, String>();
		if (!tagged.isEmpty())
			finalParameters.put("tagged", TAGGED_JOINER.join(tagged));
		return finalParameters;
	}
}
