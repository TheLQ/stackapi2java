/**
 * Copyright (C) 2013 Leon Blakey <lord.quackstar at gmail.com>
 *
 * This file is part of stackapi2java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thelq.stackexchange.api;

import java.net.URI;
import lombok.Getter;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
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
