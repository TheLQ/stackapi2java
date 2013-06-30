
package org.thelq.stackexchange.api.model;

import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Leon
 */
@Getter
@ToString
@EqualsAndHashCode
public class ResponseEntry<E> {
	@MaybeAbsent
	protected int backoff;
	protected boolean hasMore;
	protected ImmutableList<E> items;
	protected int page;
	protected int pageSize;
	protected int quotaMax;
	protected int quotaRemaining;
	protected int total;
	//TODO: Comprehensive list of types?
	protected String type;
}
