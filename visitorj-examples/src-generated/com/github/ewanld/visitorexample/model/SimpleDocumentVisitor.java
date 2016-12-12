package com.github.ewanld.visitorexample.model;


import com.github.ewanld.visitor.VisitResult;


public class SimpleDocumentVisitor implements DocumentVisitor {
	@Override
	public VisitResult enter(Document document) {
		return VisitResult.CONTINUE;
	}

	@Override
	public void leave(Document document) {
		// no op
	}

	@Override
	public VisitResult enter(Metadata metadata) {
		return VisitResult.CONTINUE;
	}

	@Override
	public void leave(Metadata metadata) {
		// no op
	}

	@Override
	public VisitResult enter(Paragraph paragraph) {
		return VisitResult.CONTINUE;
	}

	@Override
	public void leave(Paragraph paragraph) {
		// no op
	}

	@Override
	public VisitResult enter(Section section) {
		return VisitResult.CONTINUE;
	}

	@Override
	public void leave(Section section) {
		// no op
	}

}
