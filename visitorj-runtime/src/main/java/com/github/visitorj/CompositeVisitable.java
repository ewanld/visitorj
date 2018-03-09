package com.github.visitorj;

public abstract class CompositeVisitable<V> implements Visitable<V> {
	private final VisitableList<V> children = new VisitableList<>();

	public CompositeVisitable(Iterable<? extends Visitable<V>> iterable) {
		children.add(iterable);
	}

	@Override
	public Iterable<IdentifiedVisitable<V>> getVisitableChildren() {
		return children;
	}
}
