package com.github.visitorj;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.github.visitorj.codegen.GeneratorType;
import com.github.visitorj.codegen.JavaClass;
import com.github.visitorj.codegen.VisitorGeneratorService;

/**
 * This goal will say a message.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, threadSafe = true)
public class CodeGeneratorMojo extends AbstractMojo {
	@Parameter(property = "visitorName", defaultValue = "Visitor")
	private String visitorName;

	@Parameter(property = "outputDirectory", defaultValue = "${project.build.directory}/generated-sources/java")
	private String outputDirectory;

	@Parameter(property = "packageName", defaultValue = "")
	private String packageName;

	@Parameter(property = "classes")
	private List<String> classes;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Generating sources for visitor " + visitorName);
		final VisitorGeneratorService service = new VisitorGeneratorService();
		final List<JavaClass> javaClasses = classes.stream().map(JavaClass::new).collect(Collectors.toList());
		try {
			service.generate(visitorName, new File(outputDirectory), javaClasses, packageName,
					EnumSet.allOf(GeneratorType.class));
		} catch (final IOException e) {
			throw new MojoExecutionException(e.getMessage(), e);
		}
	}
}