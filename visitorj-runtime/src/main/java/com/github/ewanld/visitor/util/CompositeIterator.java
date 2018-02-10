package com.github.ewanld.visitor.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Merge a sequence of iterators in a single iterator.
 */
public class CompositeIterator<T> implements Iterator<T> {

	private final Iterator<? extends Iterator<? extends T>> iteratorsIt;
	private Iterator<? extends T> currentIterator;

	public CompositeIterator(Iterator<? extends Iterator<? extends T>> iteratorsIt) {
		this.iteratorsIt = iteratorsIt;
		currentIterator = iteratorsIt.hasNext() ? iteratorsIt.next() : null;
	}

	public CompositeIterator(Iterable<? extends Iterator<? extends T>> iterators) {
		this(iterators.iterator());
	}

	public static <T, U extends Iterable<? extends T>> CompositeIterator<T> fromIterableOfIterables(
			Iterable<U> collections) {
		final Collection<Iterator<? extends T>> iterators_list = new ArrayList<>();
		collections.forEach(iterable -> iterators_list.add(iterable.iterator()));
		return new CompositeIterator<>(iterators_list);
	}

	@Override
	public boolean hasNext() {
		if (currentIterator != null && currentIterator.hasNext()) return true;
		while (iteratorsIt.hasNext()) {
			currentIterator = iteratorsIt.next();
			if (currentIterator.hasNext()) return true;
		}
		return false;
	}

	@Override
	public T next() {
		if (currentIterator != null && currentIterator.hasNext()) return currentIterator.next();
		while (iteratorsIt.hasNext()) {
			currentIterator = iteratorsIt.next();
			if (currentIterator.hasNext()) return currentIterator.next();
		}
		throw new NoSuchElementException();
	}

}
