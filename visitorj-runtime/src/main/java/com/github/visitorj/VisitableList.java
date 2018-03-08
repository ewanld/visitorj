package com.github.visitorj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.github.visitorj.util.CompositeIterator;

/**
 * Represent a list of IdentifiedVisitable instances.
 * @param <V> The Visitor type.
 */
public final class VisitableList<V> implements Iterable<IdentifiedVisitable<V>> {
	private static final VisitableList<?> empty = new VisitableList<>();
	private final List<Iterable<? extends IdentifiedVisitable<V>>> items = new ArrayList<>();

	public VisitableList() {

	}

	public VisitableList(Iterable<? extends Visitable<V>> iterable) {
		this();
		add(iterable);
	}

	/**
	 * Add a single Visitable instance to this list.
	 */
	public void add(Visitable<V> visitable, String identifier) {
		items.add(Collections.singleton(new IdentifiedVisitable<>(visitable, identifier)));
	}

	/**
	 * Add a single Visitable instance to this list.
	 */
	public void add(Visitable<V> visitable) {
		add(visitable, null);
	}

	/**
	 * Add a collection of {@link Visitable} to this list. A reference to the {@link Iterable} instance is kept, so
	 * that
	 * if items are added or removed from the {@link Iterable}, those items will be part of this
	 * {@link VisitableList}.
	 */
	public void add(Iterable<? extends Visitable<V>> iterable, String identifier) {
		add(new IdentifiedVisitableIterable<>(iterable, identifier));
	}

	/**
	 * Add a collection of {@link Visitable} to this list. A reference to the {@link Iterable} instance is kept, so
	 * that
	 * if items are added or removed from the {@link Iterable}, those items will be part of this
	 * {@link VisitableList}.
	 */
	public void add(Iterable<? extends Visitable<V>> iterable) {
		add(new IdentifiedVisitableIterable<>(iterable, null));
	}

	/**
	 * Add a collection of {@link Visitable} to this list. A reference to the {@link Iterable} instance is kept, so
	 * that
	 * if items are added or removed from the {@link Iterable}, those items will be part of this
	 * {@link VisitableList}.
	 */
	public void add(final IdentifiedVisitableIterable<V> iterable) {
		items.add(iterable);
	}

	@SuppressWarnings("unchecked")
	public static <V> VisitableList<V> empty() {
		return (VisitableList<V>) empty;
	}

	private static class IdentifiedVisitableIterable<V> implements Iterable<IdentifiedVisitable<V>> {
		private final Iterable<? extends Visitable<V>> iterable;
		private final String identifier;

		public IdentifiedVisitableIterable(Iterable<? extends Visitable<V>> iterable, String identifier) {
			this.iterable = iterable;
			this.identifier = identifier;
		}

		@Override
		public Iterator<IdentifiedVisitable<V>> iterator() {
			return new IdentifiedVisitableIterator<>(iterable.iterator(), identifier);
		}

	}

	private static class IdentifiedVisitableIterator<V> implements Iterator<IdentifiedVisitable<V>> {
		private final Iterator<? extends Visitable<V>> iterator;
		private final String identifier;

		public IdentifiedVisitableIterator(Iterator<? extends Visitable<V>> iterator, String identifier) {
			this.iterator = iterator;
			this.identifier = identifier;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public IdentifiedVisitable<V> next() {
			return new IdentifiedVisitable<>(iterator.next(), identifier);
		}

	}

	@Override
	public Iterator<IdentifiedVisitable<V>> iterator() {
		return CompositeIterator.fromIterableOfIterables(items);
	}
}
