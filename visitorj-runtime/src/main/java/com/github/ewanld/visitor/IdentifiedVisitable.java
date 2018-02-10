package com.github.ewanld.visitor;

public final class IdentifiedVisitable<V> {
	private final Visitable<V> visitable;
	private final String identifier;

	public IdentifiedVisitable(Visitable<V> visitable, String identifier) {
		this.visitable = visitable;
		this.identifier = identifier;
	}

	public Visitable<V> get() {
		return visitable;
	}

	public String getIdentifier() {
		return identifier;
	}

}