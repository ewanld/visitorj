package com.github.ewanld.visitor;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class VisitableTest {
	@Test
	public void testAccept() {
		final Model1PrintVisitor visitor = new Model1PrintVisitor();
		final Model1 model1 = new Model1(new Model2(), Arrays.asList(new Model2(), new Model2()), new Model3());
		model1.accept(visitor);
	}

}
