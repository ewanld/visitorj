package com.github.ewanld.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Merge a sequence of iterators in a single iterator.
 */
public class CompositeIterator<T> implements Iterator<T> {

	private final Iterator<Iterator<? extends T>> iteratorsIt;
	private Iterator<? extends T> currentIterator;

	public CompositeIterator(Collection<? extends Iterator<? extends T>> iterators) {
		final List<Iterator<? extends T>> iterators_list = new ArrayList<>(iterators);
		iteratorsIt = iterators_list.iterator();
		currentIterator = iteratorsIt.next();
	}

	@Override
	public boolean hasNext() {
		if (currentIterator.hasNext()) return true;
		while (iteratorsIt.hasNext()) {
			currentIterator = iteratorsIt.next();
			if (currentIterator.hasNext()) return true;
		}
		return false;
	}

	@Override
	public T next() {
		if (currentIterator.hasNext()) return currentIterator.next();
		while (iteratorsIt.hasNext()) {
			currentIterator = iteratorsIt.next();
			if (currentIterator.hasNext()) return currentIterator.next();
		}
		throw new NoSuchElementException();
	}

}
