/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api;

import java.net.URI;

/**
 *
 * @author Leon
 */
public class QueryException extends RuntimeException {
	protected final URI uri;

	public QueryException(URI uri, String message) {
		super(message + "\nURI: " + uri);
		this.uri = uri;
	}

	public QueryException(URI uri, String message, Throwable cause) {
		super(message + "\nURI: " + uri, cause);
		this.uri = uri;
	}
}
