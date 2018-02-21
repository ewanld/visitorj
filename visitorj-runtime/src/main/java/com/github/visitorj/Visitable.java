package com.github.visitorj;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents an object that can be visited by a Visitor.
 * @param <T>
 *        The Visitor type.
 */
public interface Visitable<T> {
	/**
	 * Traverse this object structure (depth-first).<br>
	 * This method should not be overriden.
	 */
	default VisitResult accept(T visitor) {
		return accept(visitor, null);
	}

	/**
	 * Traverse this object structure (depth-first).<br>
	 * This method should not be overriden.
	 */
	default VisitResult accept(T visitor, String identifier) {
		final VisitResult result = visit(visitor, identifier);

		if (result == VisitResult.ABORT) return VisitResult.ABORT;

		if (!result.skipChildren()) {
			final Iterator<? extends IdentifiedVisitable<T>> it = getVisitableChildren().iterator();
			boolean first = true;
			while (it.hasNext()) {
				if (!first) event(VisitEvent.INBETWEEN_CHILDREN, visitor);
				first = false;
				event(VisitEvent.BEFORE_CHILD, visitor);
				final IdentifiedVisitable<T> child = it.next();
				final VisitResult childResult = child == null || child.get() == null ? VisitResult.CONTINUE
						: child.get().accept(visitor, child.getIdentifier());
				if (childResult == VisitResult.ABORT) return VisitResult.ABORT;
				event(VisitEvent.AFTER_CHILD, visitor);
				if (childResult.skipSiblings()) break;
			}
		}
		event(VisitEvent.LEAVE, visitor);
		return result;
	}

	/**
	 * Traverse this object structure (breadth-first).<br>
	 * This method should not be overriden.<br>
	 * Warning: does not call event methods.
	 */
	default void accept_breadthFirst(T visitor) {
		final Queue<IdentifiedVisitable<T>> queue = new LinkedList<>();
		queue.add(new IdentifiedVisitable<>(this, null));
		while (!queue.isEmpty()) {
			final IdentifiedVisitable<T> node = queue.remove();
			final VisitResult result = node.get().visit(visitor, node.getIdentifier());
			if (result == VisitResult.ABORT) return;
			if (result.skipSiblings()) queue.clear();
			if (!result.skipChildren()) node.get().getVisitableChildren().forEach(queue::add);
		}
	}

	/**
	 * Return an iterator on the child nodes of this object. The default implementation has no child nodes.
	 */
	default Iterable<IdentifiedVisitable<T>> getVisitableChildren() {
		return VisitableList.empty();
	}

	/**
	 * The implementation should always be the same:<br>
	 * {@code visitor.event(event, this);}
	 */
	void event(VisitEvent event, T visitor);

	/**
	 * The implementation should always be the same:<br>
	 * {@code return visitor.visit(this, identifier);}
	 */
	VisitResult visit(T visitor, String identifier);
}
