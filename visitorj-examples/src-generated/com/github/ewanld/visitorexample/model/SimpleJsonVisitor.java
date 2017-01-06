package com.github.ewanld.visitorexample.model;


import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.VisitEvent;


public class SimpleJsonVisitor implements JsonVisitor {
	@Override
	public VisitResult visit(JsonArray jsonArray) {
		return VisitResult.CONTINUE;
	}

	@Override
	public void event(VisitEvent event, JsonArray jsonArray) {
		// no op
	}

	@Override
	public VisitResult visit(JsonObject jsonObject) {
		return VisitResult.CONTINUE;
	}

	@Override
	public void event(VisitEvent event, JsonObject jsonObject) {
		// no op
	}

	@Override
	public VisitResult visit(JsonNumber jsonNumber) {
		return VisitResult.CONTINUE;
	}

	@Override
	public void event(VisitEvent event, JsonNumber jsonNumber) {
		// no op
	}

	@Override
	public VisitResult visit(JsonBoolean jsonBoolean) {
		return VisitResult.CONTINUE;
	}

	@Override
	public void event(VisitEvent event, JsonBoolean jsonBoolean) {
		// no op
	}

	@Override
	public VisitResult visit(JsonObjectProperty jsonObjectProperty) {
		return VisitResult.CONTINUE;
	}

	@Override
	public void event(VisitEvent event, JsonObjectProperty jsonObjectProperty) {
		// no op
	}

}
