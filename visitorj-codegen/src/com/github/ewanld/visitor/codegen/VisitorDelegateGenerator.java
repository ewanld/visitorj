package com.github.ewanld.visitor.codegen;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import com.github.ewanld.visitor.VisitResult;

public class VisitorDelegateGenerator extends AbstractGenerator {

	public VisitorDelegateGenerator(Writer writer) {
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
		
		writeln("public class %sVisitorDelegate implements %sVisitor {", mainClass, mainClass);
		
		writeln("	private final %sVisitor visitor;", mainClass);
		
		writeln("	public %sVisitorDelegate(%sVisitor visitor) {", mainClass, mainClass);
		writeln("		this.visitor = visitor;");
		writeln("	}");
		
		for (final JavaClass _class : classes) {
		final String c = _class.getSimpleName();
		final String c_ident = toIdent(_class);
		
		writeln("	@Override");
		writeln("	public VisitResult enter(%s %s) {", c, c_ident);
		writeln("		return visitor.enter(%s);", c_ident);
		writeln("	}\n");
		
		writeln("	@Override");
		writeln("	public void leave(%s %s) {", c, c_ident);
		writeln("		visitor.leave(%s);", c_ident);
		writeln("	}\n");
		}
		
		writeln("}");
		// @formatter:on

		writer.flush();
	}
}
