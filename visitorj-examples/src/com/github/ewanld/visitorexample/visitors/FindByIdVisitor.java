package com.github.ewanld.visitorexample.visitors;

import java.math.BigDecimal;

import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitorexample.model.JsonNumber;
import com.github.ewanld.visitorexample.model.JsonObject;
import com.github.ewanld.visitorexample.model.JsonObjectProperty;
import com.github.ewanld.visitorexample.model.SimpleJsonVisitor;

public class FindByIdVisitor extends SimpleJsonVisitor {
	private final int id;
	private JsonObject currentObject;
	private JsonObject result;

	public FindByIdVisitor(int id) {
		this.id = id;
	}

	@Override
	public VisitResult visit(JsonObject jsonObject) {
		currentObject = jsonObject;
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult visit(JsonObjectProperty p) {
		if (p.getName().equals("id") && p.getValue() instanceof JsonNumber) {
			final JsonNumber idNode = (JsonNumber) p.getValue();
			System.out.println(String.format("Visiting object with id %s...", idNode.getValue()));
			if (idNode.getValue().equals(BigDecimal.valueOf(id))) {
				result = currentObject;
				return VisitResult.ABORT;
			}
		}
		return VisitResult.CONTINUE;
	}

	public JsonObject getResult() {
		return result;
	}
}
