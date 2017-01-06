package com.github.ewanld.visitorexample.model;


import com.github.ewanld.visitor.VisitResult;


public class SimpleJsonVisitor implements JsonVisitor {
	@Override
	public VisitResult visit(JsonArray jsonArray) {
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult visit(JsonObject jsonObject) {
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult visit(JsonNumber jsonNumber) {
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult visit(JsonBoolean jsonBoolean) {
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult visit(JsonObjectProperty jsonObjectProperty) {
		return VisitResult.CONTINUE;
	}

}
