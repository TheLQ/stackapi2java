/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries;

import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.thelq.stackexchange.api.queries.site.comments.CommentDeleteQuery;
import org.thelq.stackexchange.api.queries.site.comments.CommentEditQuery;

/**
 *
 * @author Leon
 */
public class QueriesFormatTest {
	public Object[][] getQueriesDataProvider(boolean includeAbstract, boolean includeQueries) throws IOException {
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
		return params.toArray(new Object[params.size()][]);
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

	@Test(dataProvider = "queriesAllDataProvider")
	public void fluentSettersAndAddersTest(Class<?> curClass) {
		//TODO: Check actual return type instead of this guessing?
		for (Method curMethod : curClass.getDeclaredMethods()) {
			if (!curMethod.getName().startsWith("set") && !curMethod.getName().startsWith("add"))
				continue;
			if (curMethod.isSynthetic())
				continue;
			TypeVariable<Class> genericReturn = (TypeVariable<Class>) curMethod.getGenericReturnType();
			assertEquals(genericReturn.getName(), "Q", "Unknown return name");
			assertTrue(AbstractQuery.class.isAssignableFrom(genericReturn.getGenericDeclaration()), "Unknown return class");
		}
	}
}
