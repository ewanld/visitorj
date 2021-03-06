package com.github.visitorj.util;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Merge a sequence of {@link Iterable} objects in a single {@link Iterable}.
 */
public class CompositeIterable<T> implements Iterable<T> {

	private final Iterable<? extends Iterable<? extends T>> iterables;

	@SafeVarargs
	public CompositeIterable(Iterable<? extends T>... iterables) {
		this(Arrays.asList(iterables));
	}

	public CompositeIterable(Iterable<? extends Iterable<? extends T>> iterables) {
		this.iterables = iterables;
	}

	@Override
	public Iterator<T> iterator() {
		return CompositeIterator.fromIterableOfIterables(iterables);
	}

}
