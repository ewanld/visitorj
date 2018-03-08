package com.github.visitorj.codegen;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;

public class VisitorDelegateGenerator extends AbstractGenerator {

	public VisitorDelegateGenerator(Writer writer) {
		super(writer);
	}

	@Override
	public void generate(String visitorName, String packageName, Collection<? extends JavaClass> classes)
			throws IOException {
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
		
		writeln("public class %sVisitorDelegate implements %sVisitor {", visitorName, visitorName);
		
		writeln("	private final %sVisitor visitor;", visitorName);
		
		writeln("	public %sVisitorDelegate(%sVisitor visitor) {", visitorName, visitorName);
		writeln("		this.visitor = visitor;");
		writeln("	}");
		
		for (final JavaClass _class : classes) {
		final String c = _class.getSimpleName();
		final String c_ident = toIdent(_class);
		
		writeln("	@Override");
		writeln("	public VisitResult visit(%s %s, String identifier) {", c, c_ident);
		writeln("		return visitor.visit(%s, identifier);", c_ident);
		writeln("	}\n");
		
		writeln("	@Override");
		writeln("	public void event(VisitEvent event, %s %s) {", c, c_ident);
		writeln("		visitor.event(event, %s);", c_ident);
		writeln("	}\n");
		}
		
		writeln("}");
		// @formatter:on

		writer.flush();
	}
}
