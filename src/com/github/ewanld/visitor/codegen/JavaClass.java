package com.github.ewanld.visitor.codegen;

public class JavaClass {
	private final String packageName;
	private final String simpleName;
	private final boolean recursive;

	public JavaClass(String fullName, boolean recursive) {
		this.recursive = recursive;
		final int dotIndex = fullName.lastIndexOf('.');
		packageName = fullName.substring(0, dotIndex);
		simpleName = fullName.substring(dotIndex + 1);
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

	public boolean isRecursive() {
		return recursive;
	}

}
