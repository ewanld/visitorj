package com.github.ewanld.visitor;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents an object that can be visited by a Visitor.
 */
public interface Visitable<T> {
	default VisitResult accept(T visitor) {
		final VisitResult result = visitorEnter(visitor);
		if (result == VisitResult.ABORT) return VisitResult.ABORT;

		if (result != VisitResult.SKIP_CHILDREN) {
			final Iterator<Visitable<T>> it = getVisitableChildren();
			while (it.hasNext()) {
				final Visitable<T> child = it.next();
				final VisitResult childResult = child.accept(visitor);
				if (childResult == VisitResult.ABORT) return VisitResult.ABORT;
				if (childResult == VisitResult.SKIP_SIBLINGS) break;
			}
		}
		visitorLeave(visitor);
		return result;
	}

	default VisitResult accept_breadthFirst(T visitor) {
		final Queue<Visitable<T>> queue = new LinkedList<Visitable<T>>();
		queue.add(this);
		return accept_breadthFirst_private(visitor, queue);
	}

	default VisitResult accept_breadthFirst_private(T visitor, Queue<Visitable<T>> queue) {

		while (!queue.isEmpty()) {
			final Visitable<T> node = queue.remove();
			final VisitResult result = node.visitorEnter(visitor);
			if (result == VisitResult.ABORT) return VisitResult.ABORT;
			if (result == VisitResult.SKIP_SIBLINGS) queue.clear();
			if (result != VisitResult.SKIP_CHILDREN) node.getVisitableChildren().forEachRemaining(queue::add);
			node.visitorLeave(visitor);
		}
		return VisitResult.CONTINUE;
	}

	default Iterator<Visitable<T>> getVisitableChildren() {
		return Collections.emptyIterator();
	}

	VisitResult visitorEnter(T visitor);

	void visitorLeave(T visitor);

}
