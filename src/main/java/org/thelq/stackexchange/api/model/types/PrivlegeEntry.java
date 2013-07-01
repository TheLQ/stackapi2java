/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model.types;

import javax.annotation.Generated;
import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;

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
