package com.github.visitorj.examples.model;

import com.github.visitorj.examples.model.JsonVisitor;
import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;

public class JsonBoolean implements JsonElement {
	private final boolean value;

	public JsonBoolean(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
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
