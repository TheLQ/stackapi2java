/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author Leon
 */
public enum PostType {
	QUESTION, 
	ANSWER;
	
	@JsonValue
	public String jsonName() {
		return name().toLowerCase();
	}
}
