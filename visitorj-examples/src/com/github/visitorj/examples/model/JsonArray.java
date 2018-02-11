package com.github.visitorj.examples.model;

import java.util.Collection;

import com.github.visitorj.examples.model.JsonVisitor;
import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;
import com.github.visitorj.VisitableList;

public class JsonArray implements JsonElement {
	private final VisitableList<JsonVisitor> children = new VisitableList<>();

	public JsonArray(Collection<? extends JsonElement> children) {
		this.children.add(children);
	}

	@Override
	public VisitResult visit(JsonVisitor visitor, String identifier) {
		return visitor.visit(this, identifier);
	}

	@Override
	public void event(VisitEvent event, JsonVisitor visitor) {
		visitor.event(event, this);
	}

	@Override
	public VisitableList<JsonVisitor> getVisitableChildren() {
		return children;
	}
}
