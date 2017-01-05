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

	/**
	 * Traverse this object structure (breadth-first).<br>
	 * This method should not be overriden.
	 */
	default void accept_breadthFirst(T visitor) {
		final Queue<Visitable<T>> queue = new LinkedList<Visitable<T>>();
		queue.add(this);
		while (!queue.isEmpty()) {
			final Visitable<T> node = queue.remove();
			final VisitResult result = node.visitorEnter(visitor);
			if (result == VisitResult.ABORT) return;
			if (result == VisitResult.SKIP_SIBLINGS) queue.clear();
			if (result != VisitResult.SKIP_CHILDREN) node.getVisitableChildren().forEachRemaining(queue::add);
			node.visitorLeave(visitor);
		}
	}

	/**
	 * Return the child nodes of this object. The default implementation has no child nodes.
	 */
	default Iterator<Visitable<T>> getVisitableChildren() {
		return Collections.emptyIterator();
	}

	/**
	 * The implementation should always be the same:<br>
	 * {@code return visitor.enter(this);}
	 */
	VisitResult visitorEnter(T visitor);

	/**
	 * Required for double dispatch. The implementation should always be the same:<br>
	 * {@code visitor.leave(this);}
	 */
	void visitorLeave(T visitor);

}
