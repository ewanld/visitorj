package com.github.visitorj.examples.visitors;

import java.io.IOException;
import java.io.Writer;

import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;
import com.github.visitorj.examples.model.JsonArray;
import com.github.visitorj.examples.model.JsonBoolean;
import com.github.visitorj.examples.model.JsonNumber;
import com.github.visitorj.examples.model.JsonObject;
import com.github.visitorj.examples.model.JsonObjectProperty;
import com.github.visitorj.examples.model.SimpleJsonVisitor;

public class PrintJsonVisitor extends SimpleJsonVisitor {
	private final Writer writer;
	/**
	 * The current depth level, starting, at 0 for the main title.
	 */
	private int depth;

	public PrintJsonVisitor(Writer writer) {
		this.writer = writer;
	}

	@Override
	public VisitResult visit(JsonArray jsonArray, String identifier) {
		writeln("[");
		depth++;
		return VisitResult.CONTINUE;
	}

	@Override
	public void event(VisitEvent event, JsonArray jsonArray) {
		if (event == VisitEvent.LEAVE) {
			depth--;
			write("\n" + indent() + "]");
		} else if (event == VisitEvent.INBETWEEN_CHILDREN) {
			writeln(",");
		} else if (event == VisitEvent.BEFORE_CHILD) {
			write(indent());
		}
	}

	@Override
	public VisitResult visit(JsonObject jsonObject, String identifier) {
		writeln("{");
		depth++;
		return VisitResult.CONTINUE;
	}

	@Override
	public void event(VisitEvent event, JsonObject jsonObject) {
		if (event == VisitEvent.LEAVE) {
			depth--;
			write("\n" + indent() + "}");
		} else if (event == VisitEvent.INBETWEEN_CHILDREN) {
			writeln(",");
		} else if (event == VisitEvent.BEFORE_CHILD) {
			write(indent());
		}
	}

	@Override
	public VisitResult visit(JsonObjectProperty jsonObjectProperty, String identifier) {
		write(jsonObjectProperty.getName() + ": ");
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult visit(JsonBoolean jsonBoolean, String identifier) {
		write(String.valueOf(jsonBoolean.getValue()));
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult visit(JsonNumber jsonNumber, String identifier) {
		write(jsonNumber.getValue().toString());
		return VisitResult.CONTINUE;
	}

	private void write(String s) {
		try {
			writer.write(s);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeln(String s) {
		write(s + "\n");
	}

	/**
	 * Return a number of spaces according to the indentation level.
	 */
	private String indent() {
		return nCopies(" ", depth * 4);
	}

	/**
	 * Return the string s, copied n times.
	 */
	private static String nCopies(String s, int n) {
		final StringBuilder res = new StringBuilder();
		for (int i = 0; i < n; i++) {
			res.append(s);
		}
		return res.toString();
	}
}
