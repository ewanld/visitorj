package com.github.ewanld.visitorexample.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import com.github.ewanld.visitor.VisitEvent;
import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.Visitable;
import com.github.ewanld.visitorexample.model.JsonVisitor;

public class JsonObject implements JsonElement {

	private final Collection<? extends JsonObjectProperty> properties;

	public JsonObject(Collection<? extends JsonObjectProperty> properties) {
		this.properties = properties;
	}

	@Override
	public Iterator<? extends Visitable<JsonVisitor>> getVisitableChildren() {
		return properties.iterator();
	}

	public Optional<JsonElement> getProperty(String name) {
		return properties.stream().filter(p -> p.getName().equals(name)).findFirst().map(JsonObjectProperty::getValue);
	}

	@Override
	public VisitResult visit(JsonVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public void event(VisitEvent event, JsonVisitor visitor) {
		visitor.event(event, this);
	}
}
