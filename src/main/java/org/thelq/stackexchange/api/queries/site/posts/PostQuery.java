/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site.posts;

import org.thelq.stackexchange.api.model.PostEntry;

/**
 *
 * @author Leon
 */
public class PostQuery extends AbstractPostComplexFullQuery<PostSort, PostQuery, PostEntry> {
	public PostQuery() {
		super(PostSort.class, PostEntry.class, "posts/{}");
	}
}
