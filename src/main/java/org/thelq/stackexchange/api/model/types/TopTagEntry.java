/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model.types;

import lombok.Getter;
import org.thelq.stackexchange.api.model.ItemEntry;

/**
 *
 * @author Leon
 */
@Getter
public class TopTagEntry implements ItemEntry {
	protected Integer answerCount;
	protected Integer answerScore;
	protected Integer questionCount;
	protected Integer questionScore;
	protected String tagName;
	protected UserEntry userId;
}
