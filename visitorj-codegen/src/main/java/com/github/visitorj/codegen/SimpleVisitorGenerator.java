package com.github.visitorj.codegen;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import com.github.visitorj.VisitResult;

public class SimpleVisitorGenerator extends AbstractGenerator {

	public SimpleVisitorGenerator(Writer writer) {
		super(writer);
	}

	@Override
	public void generate(String visitorName, String packageName, Collection<JavaClass> classes) throws IOException {
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
		
		writeln("public class Simple%sVisitor implements %sVisitor {", visitorName, visitorName);
		
		for (final JavaClass _class : classes) {
		final String c = _class.getSimpleName();
		final String c_ident = toIdent(_class);
		
		writeln("	@Override");
		writeln("	public VisitResult visit(%s %s, String identifier) {", c, c_ident);
		writeln("		return VisitResult.CONTINUE;");
		writeln("	}\n");
		}
		
		writeln("}");
		// @formatter:on

		writer.flush();
	}
}
