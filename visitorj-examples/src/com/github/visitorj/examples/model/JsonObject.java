package com.github.visitorj.examples.model;

import java.util.Collection;
import java.util.Optional;

import com.github.visitorj.examples.model.JsonVisitor;
import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;
import com.github.visitorj.VisitableList;

public class JsonObject implements JsonElement {
	private final VisitableList<JsonVisitor> children = new VisitableList<>();
	private final Collection<? extends JsonObjectProperty> properties;

	public JsonObject(Collection<? extends JsonObjectProperty> properties) {
		this.properties = properties;
		children.add(properties);
	}

	@Override
	public VisitableList<JsonVisitor> getVisitableChildren() {
		return children;
	}

	public Optional<JsonElement> getProperty(String name) {
		return properties.stream().filter(p -> p.getName().equals(name)).findFirst()
				.map(JsonObjectProperty::getValue);
	}

	@Override
	public void event(VisitEvent event, JsonVisitor visitor) {
		visitor.event(event, this);
	}

	@Override
	public VisitResult visit(JsonVisitor visitor, String identifier) {
		return visitor.visit(this, identifier);
	}
}
