package com.github.ewanld.visitorexample.model;


import com.github.ewanld.visitor.VisitResult;


public class DocumentVisitorDelegate implements DocumentVisitor {
	private final DocumentVisitor visitor;
	public DocumentVisitorDelegate(DocumentVisitor visitor) {
		this.visitor = visitor;
	}
	@Override
	public VisitResult enter(Document document) {
		return visitor.enter(document);
	}

	@Override
	public void leave(Document document) {
		visitor.leave(document);
	}

	@Override
	public VisitResult enter(Metadata metadata) {
		return visitor.enter(metadata);
	}

	@Override
	public void leave(Metadata metadata) {
		visitor.leave(metadata);
	}

	@Override
	public VisitResult enter(Paragraph paragraph) {
		return visitor.enter(paragraph);
	}

	@Override
	public void leave(Paragraph paragraph) {
		visitor.leave(paragraph);
	}

	@Override
	public VisitResult enter(Section section) {
		return visitor.enter(section);
	}

	@Override
	public void leave(Section section) {
		visitor.leave(section);
	}

}
