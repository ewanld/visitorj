package com.github.visitorj.examples.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;
import com.github.visitorj.Visitable;
import com.github.visitorj.VisitableList;
import com.github.visitorj.examples.model.JsonVisitor;

public class JsonObjectProperty implements JsonElement {
	private final String name;
	private final JsonElement value;
	private final VisitableList<JsonVisitor> children = new VisitableList<>();

	public JsonObjectProperty(String name, JsonElement value) {
		this.name = name;
		this.value = value;
		children.add(value);
	}

	public String getName() {
		return name;
	}

	public JsonElement getValue() {
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

	@Override
	public VisitableList<JsonVisitor> getVisitableChildren() {
		return children;
	}
}