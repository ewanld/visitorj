package com.github.ewanld.visitorexample;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.github.ewanld.visitorexample.model.Document;
import com.github.ewanld.visitorexample.model.Metadata;
import com.github.ewanld.visitorexample.model.Paragraph;
import com.github.ewanld.visitorexample.model.Section;
import com.github.ewanld.visitorexample.visitors.PrintDocumentVisitor;
import com.github.ewanld.visitorexample.visitors.RemoveSectionVisitor;

public class Main {
	public static void main(String[] args) throws Exception {
		// @formatter:off
		final Document d = new Document(new Metadata("John", 3),
				noTextSection("Title", Arrays.asList(
						textSection("Introduction", "My intro."),
						noTextSection("Section 1", Arrays.asList(
								textSection("Section 1.1", "A beautiful paragraph.", "Another paragraph."),
								textSection("Section 1.2", "Paragraph 1 of section 1.2.", "A beautiful paragraph.")
						)),
						textSection("Conclusion", "My conclusion.")
				))
		);
		// @formatter:on

		final OutputStreamWriter outWriter = new OutputStreamWriter(System.out);
		if (true) {
			d.accept(new RemoveSectionVisitor("Introduction",
					new RemoveSectionVisitor("Conclusion", new PrintDocumentVisitor(outWriter))));
			d.accept(new PrintDocumentVisitor(outWriter));
		}
		d.accept_breadthFirst(new PrintDocumentVisitor(outWriter));
		outWriter.close();
	}

	public static Section noTextSection(String title, Collection<? extends Section> children) {
		return new Section(title, children);
	}

	public static Section textSection(String title, String... paragraphs) {
		final List<Paragraph> l = Arrays.asList(paragraphs).stream().map(Paragraph::new).collect(Collectors.toList());
		return new Section(title, Collections.emptyList(), l);
	}
}
