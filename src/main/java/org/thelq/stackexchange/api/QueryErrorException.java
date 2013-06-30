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
	protected final String errorName;
	protected final String errorMessage;

	public QueryErrorException(URI uri, int errorId, String errorName, String errorMessage) {
		super(uri, "Error " + errorId + "(" + errorName + ") when querying StackExchange API: " + errorMessage);
		this.errorId = errorId;
		this.errorName = errorName;
		this.errorMessage = errorMessage;
	}
}
