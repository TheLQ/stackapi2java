/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thelq.stackexchange.api.model.sort;

import org.thelq.stackexchange.api.model.SortableField;

/**
 *
 * @author Leon
 */
public enum QuestionByTagSort implements SortableField {
	ACTIVITY,
	CREATION,
	VOTES,
	HOT,
	WEEK,
	MONTH
}
