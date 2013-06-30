/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.exceptions;

import java.net.URI;
import lombok.Getter;

/**
 *
 * @author Leon
 */
@Getter
public class QueryException extends RuntimeException {
	protected final URI uri;
	protected final String rawResponse;

	public QueryException(URI uri, String rawResponse, String message) {
		super(message + "\nURI: " + uri);
		this.uri = uri;
		this.rawResponse = rawResponse;
	}

	public QueryException(URI uri, String rawResponse, String message, Throwable cause) {
		super(message + "\nURI: " + uri, cause);
		this.uri = uri;
		this.rawResponse = rawResponse;
	}
}
