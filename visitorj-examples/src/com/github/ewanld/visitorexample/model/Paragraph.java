package com.github.ewanld.visitorexample.model;

import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.Visitable;

public class Paragraph implements Visitable<DocumentVisitor> {
	private final String text;

	public Paragraph(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public VisitResult visitorEnter(DocumentVisitor visitor) {
		return visitor.enter(this);
	}

	@Override
	public void visitorLeave(DocumentVisitor visitor) {
		visitor.leave(this);
	}
}
