/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
