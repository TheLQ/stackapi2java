/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api;

import java.net.URI;
import lombok.Getter;

/**
 *
 * @author Leon
 */
@Getter
public class QueryErrorException extends QueryException {
	protected final int errorId;
	protected final String errorMessage;
	protected final String errorName;

	public QueryErrorException(URI uri, int errorId, String errorMessage, String errorName) {
		super(uri, "Error when querying StackExchange API: " + errorMessage);
		this.errorId = errorId;
		this.errorMessage = errorMessage;
		this.errorName = errorName;
	}
}
