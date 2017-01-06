package com.github.ewanld.visitorexample.model;


import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.VisitEvent;


public class SimpleJsonVisitorWithContext extends JsonVisitorWithContext {
	@Override
	protected VisitResult onVisit(JsonArray jsonArray) {
		return VisitResult.CONTINUE;
	}

	@Override
	protected void onEvent(VisitEvent event, JsonArray jsonArray) {
		// no op
	}

	@Override
	protected VisitResult onVisit(JsonObject jsonObject) {
		return VisitResult.CONTINUE;
	}

	@Override
	protected void onEvent(VisitEvent event, JsonObject jsonObject) {
		// no op
	}

	@Override
	protected VisitResult onVisit(JsonNumber jsonNumber) {
		return VisitResult.CONTINUE;
	}

	@Override
	protected void onEvent(VisitEvent event, JsonNumber jsonNumber) {
		// no op
	}

	@Override
	protected VisitResult onVisit(JsonBoolean jsonBoolean) {
		return VisitResult.CONTINUE;
	}

	@Override
	protected void onEvent(VisitEvent event, JsonBoolean jsonBoolean) {
		// no op
	}

	@Override
	protected VisitResult onVisit(JsonObjectProperty jsonObjectProperty) {
		return VisitResult.CONTINUE;
	}

	@Override
	protected void onEvent(VisitEvent event, JsonObjectProperty jsonObjectProperty) {
		// no op
	}

}
