package com.github.visitorj.examples.visitors;

import java.math.BigDecimal;

import com.github.visitorj.VisitResult;
import com.github.visitorj.examples.model.JsonNumber;
import com.github.visitorj.examples.model.JsonObject;
import com.github.visitorj.examples.model.JsonObjectProperty;
import com.github.visitorj.examples.model.SimpleJsonVisitor;

public class FindByIdVisitor extends SimpleJsonVisitor {
	private final int id;
	private JsonObject currentObject;
	private JsonObject result;

	public FindByIdVisitor(int id) {
		this.id = id;
	}

	@Override
	public VisitResult visit(JsonObject jsonObject, String identifier) {
		currentObject = jsonObject;
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult visit(JsonObjectProperty p, String identifier) {
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
