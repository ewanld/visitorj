package com.github.ewanld.visitorexample.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import com.github.ewanld.visitor.CompositeIterator;
import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.Visitable;

public class Section implements Visitable<DocumentVisitor> {
	private final Collection<? extends Section> children;
	private final Collection<? extends Paragraph> paragraphs;
	private final String title;

	public Section(String title, Collection<? extends Section> children) {
		this(title, children, Collections.emptyList());
	}

	public Section(String title, Collection<? extends Section> children, Collection<? extends Paragraph> paragraphs) {
		this.title = title;
		this.children = children;
		this.paragraphs = paragraphs;
	}

	public Collection<? extends Section> getChildren() {
		return children;
	}

	public Collection<? extends Paragraph> getParagraphs() {
		return paragraphs;
	}

	@Override
	public VisitResult accept(DocumentVisitor visitor) {
		final VisitResult result = visitor.enter(this);
		if (result == VisitResult.ABORT) return VisitResult.ABORT;

		if (result != VisitResult.SKIP_CHILDREN) {
			for (final Section s : children) {
				final VisitResult childResult = s.accept(visitor);
				if (childResult == VisitResult.ABORT) return VisitResult.ABORT;
				if (childResult == VisitResult.SKIP_SIBLINGS) break;
			}

			for (final Paragraph p : paragraphs) {
				final VisitResult childResult = p.accept(visitor);
				if (childResult == VisitResult.ABORT) return VisitResult.ABORT;
				if (childResult == VisitResult.SKIP_SIBLINGS) break;
			}
		}

		visitor.leave(this);
		return result;

	}

	public String getTitle() {
		return title;
	}

	@Override
	public VisitResult visitorEnter(DocumentVisitor visitor) {
		return visitor.enter(this);
	}

	@Override
	public void visitorLeave(DocumentVisitor visitor) {
		visitor.leave(this);
	}

	@Override
	public Iterator<Visitable<DocumentVisitor>> getVisitableChildren() {
		return new CompositeIterator<>(Arrays.asList(children.iterator(), paragraphs.iterator()));
	}
}
