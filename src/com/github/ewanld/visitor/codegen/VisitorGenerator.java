package com.github.ewanld.visitor.codegen;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import com.github.ewanld.visitor.VisitResult;

public class VisitorGenerator extends AbstractGenerator {

	public VisitorGenerator(Writer writer) {
		super(writer);
	}

	@Override
	public void generate(String packageName, Collection<JavaClass> classes) throws IOException {
		final String mainClass = classes.iterator().next().getSimpleName();

		// @formatter:off
		writeln("package %s;\n", packageName);
		writeln();
		writeln("import %s;", VisitResult.class.getName());
		writeln();
		for (final JavaClass c : classes) {
		if (!c.getPackageName().equals(packageName)) {
		writeln("import %s;", c.getFullName());
		}
		}
		writeln();
		
		writeln("public interface %sVisitor {", mainClass);
		
		for (final JavaClass _class : classes) {
		final String c = _class.getSimpleName();
		final String c_ident = toIdent(_class);
		
		writeln("	VisitResult enter(%s %s);\n", c, c_ident);
		writeln("	void leave(%s %s);\n", c, c_ident);
		}
		
		writeln("}");
		// @formatter:on

		writer.flush();
	}
}
