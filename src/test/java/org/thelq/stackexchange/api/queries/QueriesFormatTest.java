/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

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
import org.thelq.stackexchange.api.queries.site.comments.CommentDeleteQuery;
import org.thelq.stackexchange.api.queries.site.comments.CommentEditQuery;
import org.thelq.stackexchange.api.queries.site.question.AbstractQuestionByIdQuery;

/**
 *
 * @author Leon
 */
public class QueriesFormatTest {
	public Class[][] getQueriesDataProvider(boolean includeAbstract, boolean includeQueries) throws IOException {
		ClassPath classPath = ClassPath.from(getClass().getClassLoader());
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
	public Object[][] queriesAbstractDataProvider() throws IOException {
		return getQueriesDataProvider(true, false);
	}

	@DataProvider
	public Object[][] queriesDataProvider() throws IOException {
		return getQueriesDataProvider(false, true);
	}

	@DataProvider
	public Object[][] queriesAllDataProvider() throws IOException {
		return getQueriesDataProvider(true, true);
	}

	@Test(dataProvider = "queriesDataProvider")
	public void abstractNamingTest(Class<?> curClass) {
		if (curClass.getSimpleName().startsWith("Abstract"))
			assertTrue(Modifier.isAbstract(curClass.getModifiers()), curClass + " prefixed with abstract, must have abstract modifier");
	}

	@Test(dataProvider = "queriesAllDataProvider")
	public void singleConstructorTest(Class<?> curClass) {
		assertEquals(curClass.getDeclaredConstructors().length, 1, curClass + " can only have a single constructor");
	}

	@Test(dataProvider = "queriesDataProvider", dependsOnMethods = {"singleConstructorTest", "abstractNamingTest"})
	public void emptyConstructorTest(Class<?> curClass) {
		//Skip exceptional classes
		if (curClass.equals(CommentEditQuery.class) || curClass.equals(CommentDeleteQuery.class))
			return;
		assertEquals(curClass.getDeclaredConstructors()[0].getParameterTypes().length, 0, "Query " + curClass + " cannot have parameters");
	}

	@DataProvider
	public Object[][] fluentSettersAndAddersDataProvider() throws IOException {
		List<Method[]> methods = new ArrayList<Method[]>();
		for (Class[] curClassArray : getQueriesDataProvider(true, true))
			for (Method curMethod : curClassArray[0].getMethods()) {
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
		System.out.println("Current method: " + curMethod);
		Type returnType = curMethod.getGenericReturnType();
		assertNotEquals(returnType, Void.TYPE, "Cannot return void");
		if (returnType instanceof TypeVariable) {
			TypeVariable<Class> genericReturn = (TypeVariable<Class>) returnType;
			assertEquals(genericReturn.getName(), "Q", "Unknown return name");
			assertTrue(AbstractQuery.class.isAssignableFrom(genericReturn.getGenericDeclaration()), "Unknown return class");
		} else if (returnType == curMethod.getDeclaringClass())
			//Returning itself, make sure this is the final class
			assertFalse(Modifier.isAbstract(curMethod.getDeclaringClass().getModifiers()));
		else
			throw new RuntimeException("Unknown return type " + returnType + " | " + returnType.getClass());
	}

	@DataProvider
	public Object[][] noPrimativeTypesDataProvider() throws IOException {
		List<Field[]> fields = new ArrayList<Field[]>();
		for (Class[] curClassArray : getQueriesDataProvider(true, true))
			for (Field curField : curClassArray[0].getDeclaredFields()) {
				if (curClassArray[0] == AbstractQuestionByIdQuery.class && curField.getName().equals("idsRequired"))
					continue;
				fields.add(new Field[]{curField});
			}
		return fields.toArray(new Field[fields.size()][]);
	}

	@Test(dataProvider = "noPrimativeTypesDataProvider")
	public void noPrimativeTypes(Field curField) {
		assertFalse(curField.getType() == int.class, "No primative ints allowed for " + curField);
		assertFalse(curField.getType() == long.class, "No primative longs allowed for " + curField);
		assertFalse(curField.getType() == boolean.class, "No primative booleans allowed for " + curField);
	}
}
