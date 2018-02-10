package com.github.ewanld.visitor.codegen;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

public abstract class AbstractGenerator implements AutoCloseable {
	protected final Writer writer;

	public AbstractGenerator(Writer writer) {
		assert writer != null;
		this.writer = writer;

	}

	public void writeln() throws IOException {
		writeln("");
	}

	public void writeln(String format, Object... args) throws IOException {
		writer.write(String.format(format, args) + "\n");
	}

	@Override
	public void close() throws IOException {
		writer.close();
	}

	public abstract void generate(String visitorName, String packageName, Collection<JavaClass> classes)
			throws IOException;

	protected final String toIdent(JavaClass _class) {
		final String className = _class.getSimpleName();
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	protected final String escapeJavaString(String s) {
		return s.replace("\\", "\\\\").replace("\"", "\\\"");
	}

	protected final String toJavaLiteral(String s) {
		return "\"" + escapeJavaString(s) + "\"";
	}
}
