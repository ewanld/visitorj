package com.github.ewanld.visitorexample.model;

import java.util.Arrays;
import java.util.Iterator;

import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.Visitable;

public class Document implements Visitable<DocumentVisitor> {
	private final Metadata metadata;
	private final Section title;

	public Document(Metadata metadata, Section title) {
		assert metadata != null;
		assert title != null;

		this.metadata = metadata;
		this.title = title;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public Section getTitle() {
		return title;
	}

	@Override
	public Iterator<Visitable<DocumentVisitor>> getVisitableChildren() {
		return Arrays.asList(metadata, title).iterator();
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
