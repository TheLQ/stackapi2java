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
package org.thelq.stackexchange.api.model.types;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.thelq.stackexchange.api.TestUtils;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class EntryFormatTest {
	protected static final Predicate<Class<?>> NO_ENUMS_FILTER = new Predicate<Class<?>>() {
		public boolean apply(Class<?> input) {
			return !input.isEnum();
		}
	};
	

	protected static ImmutableList<Field> getEntriesFields(Predicate<Field> allowedFilter) throws IOException {
		ImmutableList.Builder<Field> fields = ImmutableList.builder();
		for (Class<?> curClass : TestUtils.loadClasses(PostEntry.class, NO_ENUMS_FILTER))
			for (Field curField : curClass.getDeclaredFields()) {
				if (allowedFilter != null && !allowedFilter.apply(curField))
					continue;
				fields.add(new Field[]{curField});
			}
		return fields.build();
	}

	@DataProvider
	public Object[][] enumsInFieldsAreNotFromAnotherClassDataProvider() throws IOException {
		return TestUtils.toTestParameters(getEntriesFields(new Predicate<Field>() {
			public boolean apply(Field input) {
				Class type = input.getType();
				return type.isEnum() && type.getDeclaringClass() != null;
			}
		}));
	}

	@Test(dataProvider = "enumsInFieldsAreNotFromAnotherClassDataProvider")
	public void enumsInFieldsAreNotFromAnotherClass(Field curField) {
		assertEquals(curField.getDeclaringClass(), curField.getType().getDeclaringClass(), "Classes do not match");
	}

	@DataProvider
	public Object[][] fieldsAllDataProvider() throws IOException {
		return TestUtils.toTestParameters(getEntriesFields(null));
	}

	@Test(dataProvider = "fieldsAllDataProvider")
	public void noPrimatives(Field curField) {
		assertFalse(curField.getType().isPrimitive(), "No primatives allowed for " + curField);
	}

	@Test(dataProvider = "fieldsAllDataProvider")
	public void noLong(Field curField) {
		assertNotEquals(curField.getType(), Long.class);
	}

	@DataProvider
	public Object[][] noExtraEnumMethods() throws IOException {
		List<Class<?>> enumClasses = new ArrayList<Class<?>>();
		for(Class<?> curClass : TestUtils.loadClasses(PostEntry.class, null)) {
			if (curClass.isEnum())
				enumClasses.add(curClass);
			//Load subtypes
			for (Class<?> curSubClass : curClass.getDeclaredClasses())
				if (curSubClass.isEnum())
					enumClasses.add(curSubClass);
		}
		return TestUtils.toTestParameters(enumClasses);
	}

	@Test(dataProvider = "noToStringEnumDataProvider")
	public void noExtraEnumMethods(Class<?> curEnum) throws NoSuchMethodException {
		assertEquals(curEnum.getDeclaredMethods().length, 2, "Enum " + curEnum.getName() + " has extra methods: "
				+ SystemUtils.LINE_SEPARATOR + StringUtils.join(curEnum.getDeclaredMethods(), SystemUtils.LINE_SEPARATOR));
	}
}
