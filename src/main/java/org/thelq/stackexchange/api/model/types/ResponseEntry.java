
package org.thelq.stackexchange.api.model.types;

import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.thelq.stackexchange.api.model.MaybeAbsent;

/**
 *
 * @author Leon
 */
@Getter
@ToString
@EqualsAndHashCode
public class ResponseEntry<E> {
	@MaybeAbsent
	protected Integer backoff;
	protected Boolean hasMore;
	protected ImmutableList<E> items;
	protected Integer page;
	protected Integer pageSize;
	protected Integer quotaMax;
	protected Integer quotaRemaining;
	protected Integer total;
	//TODO: Comprehensive list of types?
	protected String type;
}
