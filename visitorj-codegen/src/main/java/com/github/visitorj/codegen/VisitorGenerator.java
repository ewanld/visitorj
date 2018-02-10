package com.github.visitorj.codegen;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;

public class VisitorGenerator extends AbstractGenerator {

	public VisitorGenerator(Writer writer) {
		super(writer);
	}

	@Override
	public void generate(String visitorName, String packageName, Collection<JavaClass> classes) throws IOException {
		// @formatter:off
		writeln("package %s;\n", packageName);
		writeln();
		writeln("import %s;", VisitResult.class.getName());
		writeln("import %s;", VisitEvent.class.getName());
		writeln();
		for (final JavaClass c : classes) {
		if (!c.getPackageName().equals(packageName)) {
		writeln("import %s;", c.getFullName());
		}
		}
		writeln();
		
		writeln("public interface %sVisitor {", visitorName);
		
		for (final JavaClass _class : classes) {
		final String c = _class.getSimpleName();
		final String c_ident = toIdent(_class);
		
		writeln("	VisitResult visit(%s %s, String identifier);\n", c, c_ident);
		writeln("	default void event(VisitEvent event, %s %s) {}\n", c, c_ident);
		}
		
		writeln("}");
		// @formatter:on

		writer.flush();
	}
}
