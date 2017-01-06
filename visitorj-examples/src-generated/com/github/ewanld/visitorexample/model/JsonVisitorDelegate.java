package com.github.ewanld.visitorexample.model;


import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.VisitEvent;


public class JsonVisitorDelegate implements JsonVisitor {
	private final JsonVisitor visitor;
	public JsonVisitorDelegate(JsonVisitor visitor) {
		this.visitor = visitor;
	}
	@Override
	public VisitResult visit(JsonArray jsonArray) {
		return visitor.visit(jsonArray);
	}

	@Override
	public void event(VisitEvent event, JsonArray jsonArray) {
		visitor.event(event, jsonArray);
	}

	@Override
	public VisitResult visit(JsonObject jsonObject) {
		return visitor.visit(jsonObject);
	}

	@Override
	public void event(VisitEvent event, JsonObject jsonObject) {
		visitor.event(event, jsonObject);
	}

	@Override
	public VisitResult visit(JsonNumber jsonNumber) {
		return visitor.visit(jsonNumber);
	}

	@Override
	public void event(VisitEvent event, JsonNumber jsonNumber) {
		visitor.event(event, jsonNumber);
	}

	@Override
	public VisitResult visit(JsonBoolean jsonBoolean) {
		return visitor.visit(jsonBoolean);
	}

	@Override
	public void event(VisitEvent event, JsonBoolean jsonBoolean) {
		visitor.event(event, jsonBoolean);
	}

	@Override
	public VisitResult visit(JsonObjectProperty jsonObjectProperty) {
		return visitor.visit(jsonObjectProperty);
	}

	@Override
	public void event(VisitEvent event, JsonObjectProperty jsonObjectProperty) {
		visitor.event(event, jsonObjectProperty);
	}

}
