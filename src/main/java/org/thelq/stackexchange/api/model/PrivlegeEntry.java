/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model;

import javax.annotation.Generated;
import lombok.Getter;

/**
 *
 * @author Leon
 */
@Getter
public class PrivlegeEntry implements ItemEntry {
	protected String shortDescription;
	protected String description;
	protected String reputation;
}
