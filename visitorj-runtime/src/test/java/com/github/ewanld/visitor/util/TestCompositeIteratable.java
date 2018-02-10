package com.github.ewanld.visitor.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.visitorj.util.CompositeIterator;

public class TestCompositeIteratable {

	/**
	 * Test empty CompositeIterator.
	 */
	@Test
	public void testEmpty() {
		final List<Integer> l1 = Collections.emptyList();
		final List<Integer> l2 = Collections.emptyList();

		// @formatter:off
		final List<Iterator<Integer>> listOfIterators = Arrays.asList(
			l1.iterator(),
			l2.iterator()
		);
		// @formatter:on

		final List<Integer> expected = Collections.emptyList();

		final CompositeIterator<Integer> it = new CompositeIterator<>(listOfIterators);
		final List<Integer> actual = new ArrayList<>();
		it.forEachRemaining(e -> actual.add(e));

		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}

	/**
	 * Test CompositeIterator with an Iterable of iterators.
	 */
	@Test
	public void test() {
		final List<Integer> l1 = Collections.emptyList();
		final List<Integer> l2 = Arrays.asList(1, 2, 3);
		final List<Integer> l3 = Arrays.asList(4, 5, 6);
		final List<Integer> l4 = Collections.emptyList();
		final List<Integer> l5 = Collections.emptyList();
		final List<Integer> l6 = Arrays.asList(7);
		final List<Integer> l7 = Collections.emptyList();
		// @formatter:off
		final List<Iterator<Integer>> listOfIterators = Arrays.asList(
			l1.iterator(),
			l2.iterator(),
			l3.iterator(),
			l4.iterator(),
			l5.iterator(),
			l6.iterator(),
			l7.iterator()
		);
		// @formatter:on

		final List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

		final CompositeIterator<Integer> it = new CompositeIterator<>(listOfIterators);
		final List<Integer> actual = new ArrayList<>();
		it.forEachRemaining(e -> actual.add(e));

		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}

	/**
	 * Test CompositeIterator with an Iterator of iterators.
	 */
	@Test
	public void test2() {
		final List<Integer> l1 = Collections.emptyList();
		final List<Integer> l2 = Arrays.asList(1, 2, 3);
		final List<Integer> l3 = Arrays.asList(4, 5, 6);
		final List<Integer> l4 = Collections.emptyList();
		final List<Integer> l5 = Collections.emptyList();
		final List<Integer> l6 = Arrays.asList(7);
		final List<Integer> l7 = Collections.emptyList();
		// @formatter:off
		final List<Iterator<Integer>> listOfIterators = Arrays.asList(
				l1.iterator(),
				l2.iterator(),
				l3.iterator(),
				l4.iterator(),
				l5.iterator(),
				l6.iterator(),
				l7.iterator()
				);
		// @formatter:on

		final List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

		final CompositeIterator<Integer> it = new CompositeIterator<>(listOfIterators.iterator());
		final List<Integer> actual = new ArrayList<>();
		it.forEachRemaining(e -> actual.add(e));

		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}

}
