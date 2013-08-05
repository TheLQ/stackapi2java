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
package org.thelq.stackexchange.api.queries;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.ClassPath;
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

/**
 *
 * @author Leon Blakey <lord dot quackstar at gmail dot com>
 */
public class QueriesFormatTest {
	protected static List<Class> getQuerySubtypes() throws IOException {
		ImmutableList.Builder<Class> subclasses = ImmutableList.builder();
		ClassPath classPath = ClassPath.from(AbstractQuery.class.getClassLoader());
		for (ClassPath.ClassInfo curClassInfo : classPath.getTopLevelClassesRecursive(AbstractQuery.class.getPackage().getName())) {
			Class curClass = curClassInfo.load();
			if (AbstractQuery.class.isAssignableFrom(curClass))
				subclasses.add(curClass);
		}
		return subclasses.build();
	}

	@DataProvider
	public Object[][] fluentSettersAndAddersDataProvider() throws IOException {
		List<Method[]> methods = new ArrayList<Method[]>();
		for (Class curClass : getQuerySubtypes())
			for (Method curMethod : curClass.getDeclaredMethods()) {
				if (!curMethod.getName().startsWith("set") && !curMethod.getName().startsWith("add"))
					continue;
				if (curMethod.isSynthetic())
					continue;
				methods.add(new Method[]{curMethod});
			}
		return methods.toArray(new Method[methods.size()][]);
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
		List<Field[]> fields = new ArrayList<Field[]>();
		for (Class curClass : getQuerySubtypes())
			for (Field curField : curClass.getDeclaredFields()) {
				if (curField.equals(AbstractQuery.class.getDeclaredField("authRequired")))
					continue;
				fields.add(new Field[]{curField});
			}
		return fields.toArray(new Field[fields.size()][]);
	}

	@Test(dataProvider = "noPrimativeFieldsDataProvider")
	public void noPrimativeFields(Field curField) {
		assertFalse(curField.getType().isPrimitive(), "No primatives allowed for " + curField);
	}
}
