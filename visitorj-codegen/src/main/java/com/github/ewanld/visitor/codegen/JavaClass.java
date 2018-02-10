package com.github.ewanld.visitor.codegen;

/**
 * Represents a java class. We do not use {@code Class<T>} since the class may or may not exist in the classpath.
 */
public class JavaClass {
	private final String packageName;
	private final String simpleName;

	/**
	 * @param fullName
	 *            package name + class name
	 * @param recursive
	 *            True if the class references itself in its children, false otherwise.
	 */
	public JavaClass(String fullName) {
		final int dotIndex = fullName.lastIndexOf('.');
		packageName = fullName.substring(0, dotIndex);
		simpleName = fullName.substring(dotIndex + 1);
	}

	public JavaClass(Class<?> _class) {
		this(_class.getCanonicalName());
	}

	public String getPackageName() {
		return packageName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	/**
	 * Return package name + class (simple) name.
	 */
	public String getFullName() {
		return packageName + "." + simpleName;
	}
}
