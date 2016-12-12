package com.github.ewanld.visitorexample.model;


import com.github.ewanld.visitor.VisitResult;


public interface DocumentVisitor {
	VisitResult enter(Document document);

	void leave(Document document);

	VisitResult enter(Metadata metadata);

	void leave(Metadata metadata);

	VisitResult enter(Paragraph paragraph);

	void leave(Paragraph paragraph);

	VisitResult enter(Section section);

	void leave(Section section);

}
