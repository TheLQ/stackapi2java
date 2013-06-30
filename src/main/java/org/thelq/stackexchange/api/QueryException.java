/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api;

import lombok.Getter;

/**
 *
 * @author Leon
 */
@Getter
public class QueryException extends RuntimeException {
	protected final String url;
	protected final int errorId;
	protected final String errorMessage;
	protected final String errorName;

	public QueryException(String url, int errorId, String errorMessage, String errorName, Throwable cause) {
		super("Error when querying StackExchange API: " + errorMessage
				+ "\nURL: " + url, cause);
		this.url = url;
		this.errorId = errorId;
		this.errorMessage = errorMessage;
		this.errorName = errorName;
	}
}
