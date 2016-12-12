package com.github.ewanld.visitorexample.model;

import java.util.List;
import java.util.ArrayList;

import com.github.ewanld.visitor.VisitResult;


public abstract class DocumentVisitorWithContext implements DocumentVisitor {
	protected Document document;
	protected Metadata metadata;
	protected Paragraph paragraph;
	protected List<Section> sectionAncestors = new ArrayList<Section>();

	public final VisitResult enter(Document document) {
		this.document = document;
		return onEnter(document);
	}

	protected abstract VisitResult onEnter(Document document);

	public final void leave(Document document) {
		onLeave(document);
		this.document = null;
	}

	protected abstract void onLeave(Document document);

	public final VisitResult enter(Metadata metadata) {
		this.metadata = metadata;
		return onEnter(metadata);
	}

	protected abstract VisitResult onEnter(Metadata metadata);

	public final void leave(Metadata metadata) {
		onLeave(metadata);
		this.metadata = null;
	}

	protected abstract void onLeave(Metadata metadata);

	public final VisitResult enter(Paragraph paragraph) {
		this.paragraph = paragraph;
		return onEnter(paragraph);
	}

	protected abstract VisitResult onEnter(Paragraph paragraph);

	public final void leave(Paragraph paragraph) {
		onLeave(paragraph);
		this.paragraph = null;
	}

	protected abstract void onLeave(Paragraph paragraph);

	public final VisitResult enter(Section section) {
		this.sectionAncestors.add(section);
		return onEnter(section);
	}

	protected abstract VisitResult onEnter(Section section);

	public final void leave(Section section) {
		onLeave(section);
		this.sectionAncestors.remove(sectionAncestors.size() - 1);
	}

	protected abstract void onLeave(Section section);

	protected Section getSection() {
		return sectionAncestors.get(sectionAncestors.size() - 1);
	}

}
