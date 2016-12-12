package com.github.ewanld.visitorexample.visitors;

import java.util.stream.Collectors;

import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitorexample.model.Paragraph;
import com.github.ewanld.visitorexample.model.Section;
import com.github.ewanld.visitorexample.model.SimpleDocumentVisitorWithContext;

public class FindInParagraphVisitor extends SimpleDocumentVisitorWithContext {
	private final String text;
	private final boolean firstMatch;

	public FindInParagraphVisitor(String text, boolean firstMatch) {
		this.text = text;
		this.firstMatch = firstMatch;
	}

	public void log(String format, Object... args) {
		System.out.println(String.format(format, args));
	}

	@Override
	protected VisitResult onEnter(Paragraph paragraph) {
		log("Searching in '%s'", paragraph.getText());
		if (paragraph.getText().contains(text)) {
			final String path = sectionAncestors.stream().skip(1).map(Section::getTitle)
					.collect(Collectors.joining("/"));
			log("*** Text '%s' was found in section '%s'", text, path);
			return firstMatch ? VisitResult.ABORT : VisitResult.SKIP_SIBLINGS;
		}
		return VisitResult.CONTINUE;
	}
}
