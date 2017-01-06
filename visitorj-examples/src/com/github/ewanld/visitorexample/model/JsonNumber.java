package com.github.ewanld.visitorexample.model;

import java.math.BigDecimal;

import com.github.ewanld.visitor.VisitEvent;
import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitorexample.model.JsonVisitor;

public class JsonNumber implements JsonElement {
	private final BigDecimal value;

	public JsonNumber(BigDecimal value) {
		this.value = value;
	}

	@Override
	public VisitResult visit(JsonVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public void event(VisitEvent event, JsonVisitor visitor) {
		visitor.event(event, this);
	}

	public BigDecimal getValue() {
		return value;
	}

}
