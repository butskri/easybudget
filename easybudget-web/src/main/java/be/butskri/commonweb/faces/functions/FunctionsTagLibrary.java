package be.butskri.commonweb.faces.functions;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.sun.facelets.tag.AbstractTagLibrary;

public class FunctionsTagLibrary extends AbstractTagLibrary {

	public static final String NAMESPACE = "http://www.butskri.be/commons/functions";
	public static final FunctionsTagLibrary INSTANCE = new FunctionsTagLibrary();

	public FunctionsTagLibrary() {
		super(NAMESPACE);

		addStaticMethodsAsFunctions(Functions.class);
	}

	private void addStaticMethodsAsFunctions(Class<?> clazz) {
		try {
			Method[] methods = clazz.getMethods();

			for (int i = 0; i < methods.length; i++) {
				if (Modifier.isStatic(methods[i].getModifiers())) {
					this.addFunction(methods[i].getName(), methods[i]);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
