package com.github.visitorj.codegen;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;

public class SimpleVisitorWithContextGenerator extends AbstractGenerator {

	public SimpleVisitorWithContextGenerator(Writer writer) {
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
		
		writeln("public class Simple%sVisitorWithContext extends %sVisitorWithContext {", visitorName, visitorName);
		
		for (final JavaClass _class : classes) {
		final String c = _class.getSimpleName();
		final String c_ident = toIdent(_class);
		
		writeln("	@Override");
		writeln("	protected VisitResult onVisit(%s %s, String identifier) {", c, c_ident);
		writeln("		return VisitResult.CONTINUE;");
		writeln("	}\n");
		
		writeln("	@Override");
		writeln("	protected void onEvent(VisitEvent event, %s %s) {", c, c_ident);
		writeln("		// no op");
		writeln("	}\n");
		}
		
		writeln("}");
		// @formatter:on

		writer.flush();
	}
}
