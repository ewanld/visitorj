package com.github.visitorj.codegen;

import java.io.Writer;
import java.util.function.Function;

public enum GeneratorType {
	// @formatter:off
	VISITOR                    (VisitorGenerator::new                 , "%sVisitor"),
	SIMPLE_VISITOR             (SimpleVisitorGenerator::new           , "Simple%sVisitor"),
	VISITOR_WITH_CONTEXT       (VisitorWithContextGenerator::new      , "%sVisitorWithContext"),
	SIMPLE_VISITOR_WITH_CONTEXT(SimpleVisitorWithContextGenerator::new, "Simple%sVisitorWithContext"),
	VISITOR_DELEGATE           (VisitorDelegateGenerator::new         , "%sVisitorDelegate");
	// @formatter:on

	private final Function<Writer, AbstractGenerator> generatorSupplier;
	private final String classNameTemplate;

	GeneratorType(Function<Writer, AbstractGenerator> generatorSupplier, String classNameTemplate) {
		this.generatorSupplier = generatorSupplier;
		this.classNameTemplate = classNameTemplate;
	}

	public String getClassNameTemplate() {
		return classNameTemplate;
	}

	public Function<Writer, AbstractGenerator> getGeneratorSupplier() {
		return generatorSupplier;
	}

}