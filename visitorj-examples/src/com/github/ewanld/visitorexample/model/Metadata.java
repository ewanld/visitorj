package com.github.ewanld.visitorexample.model;

import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.Visitable;

public class Metadata implements Visitable<DocumentVisitor> {
	private final String author;
	private final int version;

	public Metadata(String author, int version) {
		this.author = author;
		this.version = version;
	}

	public String getAuthor() {
		return author;
	}

	public int getVersion() {
		return version;
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
