package com.github.ewanld.visitorexample.model;


import com.github.ewanld.visitor.VisitResult;


public class SimpleDocumentVisitorWithContext extends DocumentVisitorWithContext {
	@Override
	protected VisitResult onEnter(Document document) {
		return VisitResult.CONTINUE;
	}

	@Override
	protected void onLeave(Document document) {
		// no op
	}

	@Override
	protected VisitResult onEnter(Metadata metadata) {
		return VisitResult.CONTINUE;
	}

	@Override
	protected void onLeave(Metadata metadata) {
		// no op
	}

	@Override
	protected VisitResult onEnter(Paragraph paragraph) {
		return VisitResult.CONTINUE;
	}

	@Override
	protected void onLeave(Paragraph paragraph) {
		// no op
	}

	@Override
	protected VisitResult onEnter(Section section) {
		return VisitResult.CONTINUE;
	}

	@Override
	protected void onLeave(Section section) {
		// no op
	}

}
