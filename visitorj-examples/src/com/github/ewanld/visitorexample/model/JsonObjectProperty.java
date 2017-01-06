package com.github.ewanld.visitorexample.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import com.github.ewanld.visitor.VisitEvent;
import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.Visitable;
import com.github.ewanld.visitorexample.model.JsonVisitor;

public class JsonObjectProperty implements JsonElement {
	private final String name;
	private final JsonElement value;

	public JsonObjectProperty(String name, JsonElement value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public JsonElement getValue() {
		return value;
	}

	@Override
	public VisitResult visit(JsonVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public void event(VisitEvent event, JsonVisitor visitor) {
		visitor.event(event, this);
	}

	@Override
	public Iterator<? extends Visitable<JsonVisitor>> getVisitableChildren() {
		return Collections.singleton(value).iterator();
	}
}