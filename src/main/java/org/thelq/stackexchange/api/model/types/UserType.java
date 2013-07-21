/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model.types;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author Leon
 */
public enum UserType {
	UNREGISTERED, REGISTERED, MODERATOR, DOES_NOT_EXIST;

	@JsonValue
	public String jsonValue() {
		return name().toLowerCase();
	}
	
}
