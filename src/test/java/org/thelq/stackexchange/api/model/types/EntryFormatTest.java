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
/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.model.types;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class EntryFormatTest {
	protected static ImmutableList<Class> getEntries() throws IOException {
		Class baseClass = PostEntry.class;
		ImmutableList.Builder<Class> entries = ImmutableList.builder();
		ClassPath classPath = ClassPath.from(baseClass.getClassLoader());
		for (ClassPath.ClassInfo curClassInfo : classPath.getTopLevelClassesRecursive(baseClass.getPackage().getName())) {
			Class curClass = curClassInfo.load();
			if (curClass.isEnum())
				//Skip
				continue;
			entries.add(curClass);
		}
		return entries.build();
	}

	protected static Object[][] getEntriesFields(Predicate<Field> allowedFilter) throws IOException {
		List<Field[]> fields = new ArrayList<Field[]>();
		for (Class curClass : getEntries())
			for (Field curField : curClass.getDeclaredFields()) {
				if (allowedFilter != null && !allowedFilter.apply(curField))
					continue;
				fields.add(new Field[]{curField});
			}
		return fields.toArray(new Field[fields.size()][]);
	}

	@DataProvider
	public Object[][] enumsInFieldsAreNotFromAnotherClassDataProvider() throws IOException {
		return getEntriesFields(new Predicate<Field>() {
			public boolean apply(Field input) {
				Class type = input.getType();
				return type.isEnum() && type.getDeclaringClass() != null;
			}
		});
	}

	@Test(dataProvider = "enumsInFieldsAreNotFromAnotherClassDataProvider")
	public void enumsInFieldsAreNotFromAnotherClass(Field curField) {
		assertEquals(curField.getDeclaringClass(), curField.getType().getDeclaringClass(), "Classes do not match");
	}

	@DataProvider
	public Object[][] fieldsAllDataProvider() throws IOException {
		return getEntriesFields(null);
	}

	@Test(dataProvider = "fieldsAllDataProvider")
	public void noPrimatives(Field curField) {
		assertFalse(curField.getType().isPrimitive(), "No primatives allowed for " + curField);
	}

	@Test(dataProvider = "fieldsAllDataProvider")
	public void noLong(Field curField) {
		assertNotEquals(curField.getType(), Long.class);
	}
}
