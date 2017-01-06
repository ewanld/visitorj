package com.github.ewanld.visitorexample.model;

import java.util.Collection;
import java.util.Iterator;

import com.github.ewanld.visitor.VisitEvent;
import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.Visitable;
import com.github.ewanld.visitorexample.model.JsonVisitor;

public class JsonArray implements JsonElement {
	private final Collection<? extends JsonElement> children;

	public JsonArray(Collection<? extends JsonElement> children) {
		this.children = children;
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
		return children.iterator();
	}
}
