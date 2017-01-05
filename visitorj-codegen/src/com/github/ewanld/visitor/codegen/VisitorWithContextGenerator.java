package com.github.ewanld.visitor.codegen;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.ewanld.visitor.VisitResult;

public class VisitorWithContextGenerator extends AbstractGenerator {

	public VisitorWithContextGenerator(Writer writer) {
		super(writer);
	}

	@Override
	public void generate(String packageName, Collection<JavaClass> classes) throws IOException {
		final String mainClass = classes.iterator().next().getSimpleName();

		// @formatter:off
		writeln("package %s;\n", packageName);
		writeln("import %s;", List.class.getName());
		writeln("import %s;", ArrayList.class.getName());
		writeln();
		writeln("import %s;", VisitResult.class.getName());
		writeln();
		for (final JavaClass c : classes) {
		if (!c.getPackageName().equals(packageName)) {
		writeln("import %s;", c.getFullName());
		}
		}
		writeln();
		
		writeln("public abstract class %sVisitorWithContext implements %sVisitor {", mainClass, mainClass);
		
		for (final JavaClass _class : classes) {
		final String c = _class.getSimpleName();
		final String c_ident = toIdent(_class);
		writeln("	protected List<%s> %sAncestors = new ArrayList<%s>();", c, c_ident, c);
		}
		writeln();
		
		for (final JavaClass _class : classes) {
		final String c = _class.getSimpleName();
		final String c_ident = toIdent(_class);
		
		writeln("	public final VisitResult enter(%s %s) {", c, c_ident);
		writeln("		this.%sAncestors.add(%s);", c_ident, c_ident);
		writeln("		return onEnter(%s);", c_ident);
		writeln("	}\n");
		
		writeln("	protected abstract VisitResult onEnter(%s %s);\n", c, c_ident);
		
		writeln("	public final void leave(%s %s) {", c, c_ident);
		writeln("		onLeave(%s);", c_ident);
		writeln("		this.%sAncestors.remove(%sAncestors.size() - 1);", c_ident, c_ident);
		writeln("	}\n");
		
		writeln("	protected abstract void onLeave(%s %s);\n", c, c_ident);
		
		writeln("	protected %s get%s() {", c, c);
		writeln("		return %sAncestors.get(%sAncestors.size() - 1);", c_ident, c_ident);
		writeln("	}\n");
		}
		
		writeln("}");
		// @formatter:on

		writer.flush();
	}
}
