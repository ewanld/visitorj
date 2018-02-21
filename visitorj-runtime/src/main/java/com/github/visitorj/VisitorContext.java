package com.github.visitorj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains a stack of the parent Visitable elements, to provide context when visiting an object.
 * @param <V>
 *        The visitor type.
 */
public class VisitorContext<V> {
	private final List<IdentifiedVisitable<V>> elements = new ArrayList<>();

	public void push(String identifier, Visitable<V> object) {
		elements.add(new IdentifiedVisitable<>(object, identifier));
	}

	public IdentifiedVisitable<V> pop() {
		return elements.remove(elements.size() - 1);
	}

	public Collection<String> getPath() {
		return elements.stream().map(IdentifiedVisitable::getIdentifier).collect(Collectors.toList());
	}

	public String getPathAsString() {
		return elements.stream().map(IdentifiedVisitable::getIdentifier).collect(Collectors.joining("/"));
	}

	public IdentifiedVisitable<V> getCurrent() {
		return elements.get(elements.size() - 1);
	}

	@SuppressWarnings("unchecked")
	public <T extends Visitable<V>> T getClosest(Class<T> type, String identifier) {
		for (int i = elements.size() - 1; i >= 0; i--) {
			final IdentifiedVisitable<V> child = elements.get(i);
			if (type.isInstance(child.get()) && (identifier == null || identifier.equals(child.getIdentifier()))) {
				return (T) child.get();
			}
		}
		return null;
	}

	public <T extends Visitable<V>> T getClosest(Class<T> type) {
		return getClosest(type, null);
	}

	public List<IdentifiedVisitable<V>> getElements() {
		return elements;
	}

}
