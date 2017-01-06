package com.github.ewanld.visitor;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents an object that can be visited by a Visitor.
 * 
 * @param <T>
 *            The Visitor type.
 */
public interface Visitable<T> {
	/**
	 * Traverse this object structure (depth-first).<br>
	 * This method should not be overriden.
	 */
	default VisitResult accept(T visitor) {
		final VisitResult result = visit(visitor);
		if (result == VisitResult.ABORT) return VisitResult.ABORT;

		if (result != VisitResult.SKIP_CHILDREN) {
			final Iterator<? extends Visitable<T>> it = getVisitableChildren();
			boolean first = true;
			while (it.hasNext()) {
				if (!first) event(VisitEvent.INBETWEEN_CHILDREN, visitor);
				first = false;
				event(VisitEvent.BEFORE_CHILD, visitor);
				final Visitable<T> child = it.next();
				final VisitResult childResult = child.accept(visitor);
				if (childResult == VisitResult.ABORT) return VisitResult.ABORT;
				event(VisitEvent.AFTER_CHILD, visitor);
				if (childResult == VisitResult.SKIP_SIBLINGS) break;
			}
		}
		event(VisitEvent.LEAVE, visitor);
		return result;
	}

	/**
	 * Traverse this object structure (breadth-first).<br>
	 * This method should not be overriden.
	 */
	default void accept_breadthFirst(T visitor) {
		final Queue<Visitable<T>> queue = new LinkedList<Visitable<T>>();
		queue.add(this);
		while (!queue.isEmpty()) {
			final Visitable<T> node = queue.remove();
			final VisitResult result = node.visit(visitor);
			if (result == VisitResult.ABORT) return;
			if (result == VisitResult.SKIP_SIBLINGS) queue.clear();
			if (result != VisitResult.SKIP_CHILDREN) node.getVisitableChildren().forEachRemaining(queue::add);
			node.event(VisitEvent.LEAVE, visitor);
		}
	}

	/**
	 * Return the child nodes of this object. The default implementation has no child nodes.
	 */
	default Iterator<? extends Visitable<T>> getVisitableChildren() {
		return Collections.emptyIterator();
	}

	/**
	 * The implementation should always be the same:<br>
	 * {@code return visitor.enter(this);}
	 */
	void event(VisitEvent event, T visitor);

	/**
	 * The implementation should always be the same:<br>
	 * {@code return visitor.enter(this);}
	 */
	VisitResult visit(T visitor);

}
