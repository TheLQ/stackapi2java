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
package org.thelq.stackexchange.api.queries;

import com.google.common.base.Predicate;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.NoInjection;
import org.testng.annotations.Test;
import org.thelq.stackexchange.api.TestUtils;

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class QueriesFormatTest {
	protected static final Predicate<Class<?>> QUERY_SUBTYPES_FILTER = new Predicate<Class<?>>() {
		public boolean apply(Class<?> input) {
			return AbstractQuery.class.isAssignableFrom(input);
		}
	};

	@DataProvider
	public Object[][] fluentSettersAndAddersDataProvider() throws IOException {
		List<Method> methods = new ArrayList<Method>();
		for (Class<?> curClass : TestUtils.loadClasses(AbstractQuery.class, QUERY_SUBTYPES_FILTER))
			for (Method curMethod : curClass.getDeclaredMethods()) {
				if (!curMethod.getName().startsWith("set") && !curMethod.getName().startsWith("add"))
					continue;
				if (curMethod.isSynthetic())
					continue;
				methods.add(curMethod);
			}
		return TestUtils.toTestParameters(methods);
	}

	@Test(dataProvider = "fluentSettersAndAddersDataProvider")
	public void fluentSettersAndAddersTest(@NoInjection Method curMethod) {
		//TODO: Check actual return type instead of this guessing?
		Class<?> curClass = curMethod.getDeclaringClass();
		assertTrue(Modifier.isPublic(curClass.getModifiers()), "Must be public");
		Type returnType = curMethod.getGenericReturnType();
		assertNotEquals(returnType, Void.TYPE, "Cannot return void");
		if (returnType instanceof TypeVariable) {
			TypeVariable<Class> genericReturn = (TypeVariable<Class>) returnType;
			assertEquals(genericReturn.getName(), "Q", "Unknown return name");
			assertTrue(AbstractQuery.class.isAssignableFrom(genericReturn.getGenericDeclaration()), "Unknown return class");
		} else if (returnType == curClass)
			//Returning itself, make sure this is the final class
			assertFalse(Modifier.isAbstract(curClass.getModifiers()));
		else
			throw new RuntimeException("Unknown return type " + returnType + " | " + returnType.getClass());
	}

	@DataProvider
	public Object[][] noPrimativeFieldsDataProvider() throws IOException, NoSuchFieldException {
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> curClass : TestUtils.loadClasses(AbstractQuery.class, QUERY_SUBTYPES_FILTER))
			for (Field curField : curClass.getDeclaredFields()) {
				if (curField.equals(AbstractQuery.class.getDeclaredField("authRequired")))
					continue;
				fields.add(curField);
			}
		return TestUtils.toTestParameters(fields);
	}

	@Test(dataProvider = "noPrimativeFieldsDataProvider")
	public void noPrimativeFields(Field curField) {
		assertFalse(curField.getType().isPrimitive(), "No primatives allowed for " + curField);
	}
}
