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
 * @author Leon
 */
public class QueriesFormatTest {
	protected static Class[][] getQueriesDataProvider(boolean includeAbstract, boolean includeQueries) throws IOException {
		ClassPath classPath = ClassPath.from(QueriesFormatTest.class.getClassLoader());
		List<Class[]> params = new ArrayList<Class[]>();
		for (ClassPath.ClassInfo curClassInfo : classPath.getTopLevelClassesRecursive(AbstractQuery.class.getPackage().getName())) {
			Class curClass = curClassInfo.load();
			if (curClass.isInterface() || curClass.isEnum())
				//Skip
				continue;
			else if (Modifier.isAbstract(curClass.getModifiers())) {
				if (includeAbstract)
					params.add(new Class[]{curClass});
			} else if (includeQueries)
				params.add(new Class[]{curClass});
		}
		return params.toArray(new Class[params.size()][]);
	}

	@DataProvider
	public Object[][] fluentSettersAndAddersDataProvider() throws IOException {
		List<Method[]> methods = new ArrayList<Method[]>();
		for (Class[] curClassArray : getQueriesDataProvider(true, true))
			for (Method curMethod : curClassArray[0].getDeclaredMethods()) {
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
	public Object[][] noPrimativeFieldsDataProvider() throws IOException, NoSuchFieldException {
		List<Field[]> fields = new ArrayList<Field[]>();
		for (Class curClass : getQuerySubtypes())
			for (Field curField : curClass.getDeclaredFields()) {
				if (curField == AbstractQuery.class.getDeclaredField("authRequired"))
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
