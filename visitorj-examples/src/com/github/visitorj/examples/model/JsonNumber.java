package com.github.visitorj.examples.model;

import java.math.BigDecimal;

import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;
import com.github.visitorj.examples.model.JsonVisitor;

public class JsonNumber implements JsonElement {
	private final BigDecimal value;

	public JsonNumber(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
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
