package com.github.visitorj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <V>
 *        The visitor type.
 */
public class VisitorContext<V> {
	private final List<ContextElement<V>> elements = new ArrayList<>();

	public void push(String identifier, Visitable<V> object) {
		elements.add(new ContextElement<>(identifier, object));
	}

	public ContextElement<V> pop() {
		return elements.remove(elements.size() - 1);
	}

	public Collection<String> getPath() {
		return elements.stream().map(ContextElement::getIdentifier).collect(Collectors.toList());
	}

	public String getPathAsString() {
		return elements.stream().map(ContextElement::getIdentifier).collect(Collectors.joining("/"));
	}

	public ContextElement<V> getCurrent() {
		return elements.get(elements.size() - 1);
	}

	public List<ContextElement<V>> getElements() {
		return elements;
	}

	public static class ContextElement<V> {
		private final String identifier;
		private final Visitable<V> object;

		public ContextElement(String identifier, Visitable<V> object) {
			this.identifier = identifier;
			this.object = object;
		}

		public String getIdentifier() {
			return identifier;
		}

		public Visitable<V> getObject() {
			return object;
		}

	}
}
