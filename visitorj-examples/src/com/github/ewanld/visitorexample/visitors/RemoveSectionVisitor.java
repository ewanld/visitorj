package com.github.ewanld.visitorexample.visitors;

import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitorexample.model.DocumentVisitor;
import com.github.ewanld.visitorexample.model.DocumentVisitorDelegate;
import com.github.ewanld.visitorexample.model.Section;

public class RemoveSectionVisitor extends DocumentVisitorDelegate {
	private final String title;

	public RemoveSectionVisitor(String title, DocumentVisitor v) {
		super(v);
		assert title != null;
		this.title = title;
	}

	@Override
	public VisitResult enter(Section section) {
		if (section.getTitle().equalsIgnoreCase(title)) return VisitResult.SKIP_CHILDREN;
		return super.enter(section);
	}

}
