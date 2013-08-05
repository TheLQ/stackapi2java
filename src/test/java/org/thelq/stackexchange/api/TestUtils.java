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
package org.thelq.stackexchange.api;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author Leon
 */
public class TestUtils {
	public static ImmutableList<Class<?>> loadClasses(Class<?> baseClass, Predicate<Class<?>> allowedClasses) throws IOException {
		ImmutableList.Builder<Class<?>> subclasses = ImmutableList.builder();
		ClassPath classPath = ClassPath.from(baseClass.getClassLoader());
		for (ClassPath.ClassInfo curClassInfo : classPath.getTopLevelClassesRecursive(baseClass.getPackage().getName())) {
			Class<?> curClass = curClassInfo.load();
			if (allowedClasses != null && !allowedClasses.apply(curClass))
				continue;
			subclasses.add(curClass);
		}
		return subclasses.build();
	}

	public static Object[][] toTestParameters(Collection<?> rawParams) {
		Object[][] testParams = new Object[rawParams.size()][];
		int counter = 0;
		for (Object curRawParam : rawParams)
			testParams[counter++] = new Object[]{curRawParam};
		return testParams;
	}
}
