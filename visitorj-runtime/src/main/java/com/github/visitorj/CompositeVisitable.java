package com.github.visitorj;

public abstract class CompositeVisitable<V> implements Visitable<V> {
	private final Iterable<IdentifiedVisitable<V>> children;

	public CompositeVisitable(Iterable<IdentifiedVisitable<V>> children) {
		this.children = children;
	}

	@Override
	public Iterable<IdentifiedVisitable<V>> getVisitableChildren() {
		return children;
	}

}
