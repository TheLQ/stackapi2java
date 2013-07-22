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
 * @author Leon
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
				Class type = curField.getType();
				if (!type.isEnum() || type.getDeclaringClass() == null)
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
}
