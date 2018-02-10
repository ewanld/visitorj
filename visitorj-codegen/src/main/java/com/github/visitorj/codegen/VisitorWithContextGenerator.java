package com.github.visitorj.codegen;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;
import com.github.visitorj.VisitorContext;

public class VisitorWithContextGenerator extends AbstractGenerator {

	public VisitorWithContextGenerator(Writer writer) {
		super(writer);
	}

	@Override
	public void generate(String visitorName, String packageName, Collection<JavaClass> classes) throws IOException {
		writeln("package %s;\n", packageName);
		writeln("import %s;", List.class.getName());
		writeln("import %s;", ArrayList.class.getName());
		writeln();
		writeln("import %s;", VisitResult.class.getName());
		writeln("import %s;", VisitEvent.class.getName());
		writeln("import %s;", VisitorContext.class.getName());
		writeln();
		for (final JavaClass c : classes) {
			if (!c.getPackageName().equals(packageName)) {
				writeln("import %s;", c.getFullName());
			}
		}
		writeln();

		writeln("public abstract class %sVisitorWithContext implements %sVisitor {", visitorName, visitorName);
		writeln("	protected final VisitorContext<%sVisitor> context = new VisitorContext<>();", visitorName);

		// for (final JavaClass _class : classes) {
		// final String c = _class.getSimpleName();
		// final String c_ident = toIdent(_class);
		// writeln(" protected List<? extends Visitable<%s> = new ArrayList<%s>();", c, c_ident, c);
		// }
		writeln();

		for (final JavaClass _class : classes) {
			final String c = _class.getSimpleName();
			final String c_ident = toIdent(_class);

			writeln("	public final VisitResult visit(%s %s, String identifier) {", c, c_ident);
			writeln("		context.push(%s, %s);", toJavaLiteral(c_ident), c_ident);
			// writeln(" this.%sAncestors.add(%s);", c_ident, c_ident);
			writeln("		return onVisit(%s, identifier);", c_ident);
			writeln("	}\n");

			writeln("	protected abstract VisitResult onVisit(%s %s, String identifier);\n", c, c_ident);

			writeln("	public final void event(VisitEvent event, %s %s) {", c, c_ident);
			writeln("		onEvent(event, %s);", c_ident);
			writeln("		if (event == VisitEvent.LEAVE) context.pop();");
			// writeln(" this.%sAncestors.remove(%sAncestors.size() - 1);", c_ident, c_ident);
			writeln("	}\n");

			writeln("	protected void onEvent(VisitEvent event, %s %s) {}\n", c, c_ident);

			// writeln(" protected %s get%s() {", c, c);
			// writeln(" return %sAncestors.get(%sAncestors.size() - 1);", c_ident, c_ident);
			// writeln(" }\n");
		}

		writeln("	public VisitorContext<%sVisitor> getContext() {", visitorName);
		writeln("		return context;");
		writeln("	}");

		writeln("}");
		writer.flush();
	}
}
