package com.github.ewanld.visitorexample.visitors;

import java.io.IOException;
import java.io.Writer;

import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitorexample.model.Document;
import com.github.ewanld.visitorexample.model.Metadata;
import com.github.ewanld.visitorexample.model.Paragraph;
import com.github.ewanld.visitorexample.model.Section;
import com.github.ewanld.visitorexample.model.SimpleDocumentVisitor;

public class PrintDocumentVisitor extends SimpleDocumentVisitor {
	private final Writer writer;
	/**
	 * The current depth level, starting, at 0 for the main title.
	 */
	private int depth;

	public PrintDocumentVisitor(Writer writer) {
		this.writer = writer;
	}

	@Override
	public VisitResult enter(Document document) {
		write("Begin document");
		depth++;
		return VisitResult.CONTINUE;
	}

	@Override
	public void leave(Document document) {
		depth--;
		write("End document");
	}

	@Override
	public VisitResult enter(Metadata metadata) {
		write(String.format("Author : %s", metadata.getAuthor()));
		write(String.format("Version: %s", metadata.getVersion()));
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult enter(Paragraph paragraph) {
		write(paragraph.getText());
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult enter(Section section) {
		write(section.getTitle());
		depth++;
		return VisitResult.CONTINUE;
	}

	@Override
	public void leave(Section section) {
		depth--;
	}

	private void write(String s) {
		try {
			writer.write(nCopies(" ", depth * 4) + s + "\n");
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
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
