package com.github.ewanld.visitor.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.visitorj.util.CompositeIterable;

public class TestCompositeIterable {

	/**
	 * Test empty CompositeIterable
	 */
	@Test
	public void testEmpty() {
		final List<Integer> l1 = Collections.emptyList();
		final List<Integer> l2 = Collections.emptyList();

		// @formatter:off
		final List<Iterable<Integer>> listOfIterables = Arrays.asList(
			l1,
			l2
		);
		// @formatter:on

		final List<Integer> expected = Collections.emptyList();

		final CompositeIterable<Integer> it = new CompositeIterable<>(listOfIterables);
		final List<Integer> actual = new ArrayList<>();
		it.iterator().forEachRemaining(e -> actual.add(e));

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
		final List<Iterable<Integer>> listOfIterables = Arrays.asList(
			l1,
			l2,
			l3,
			l4,
			l5,
			l6,
			l7
		);
		// @formatter:on

		final List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

		final CompositeIterable<Integer> it = new CompositeIterable<>(listOfIterables);
		final List<Integer> actual = new ArrayList<>();
		it.iterator().forEachRemaining(e -> actual.add(e));

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
		final List<Iterable<Integer>> listOfIterables = Arrays.asList(
			l1,
			l2,
			l3,
			l4,
			l5,
			l6,
			l7
		);
		// @formatter:on

		final List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

		final CompositeIterable<Integer> it = new CompositeIterable<>(listOfIterables);
		final List<Integer> actual = new ArrayList<>();
		it.iterator().forEachRemaining(e -> actual.add(e));

		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}

}
