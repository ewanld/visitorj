package com.github.ewanld.visitor.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

/**
 * Entry point for the code generation facility.
 */
public class CodeGeneratorService {
	public void generateAll(File outputDir, List<JavaClass> classes, String packageName,
			EnumSet<GeneratorType> toBeGenerated) throws IOException {
		final File srcDir = new File(outputDir.getPath() + "/" + packageName.replace('.', '/'));
		srcDir.mkdirs();

		final String mainClassName = classes.iterator().next().getSimpleName();

		for (final GeneratorType e : toBeGenerated) {
			final String className = String.format(e.getClassNameTemplate(), mainClassName);
			final FileWriter writer = new FileWriter(String.format("%s/%s.java", srcDir, className));
			final AbstractGenerator generator = e.getGeneratorSupplier().apply(writer);
			generator.generate(packageName, classes);
			generator.close();
		}
	}

	/**
	 * Convenience method.
	 */
	public void generateAll(File outputDir, List<JavaClass> classes, String packageName) throws IOException {
		generateAll(outputDir, classes, packageName, EnumSet.allOf(GeneratorType.class));
	}
}
