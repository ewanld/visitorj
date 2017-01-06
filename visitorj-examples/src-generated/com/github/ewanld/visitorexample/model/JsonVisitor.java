package com.github.ewanld.visitorexample.model;


import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.VisitEvent;


public interface JsonVisitor {
	VisitResult visit(JsonArray jsonArray);

	void event(VisitEvent event, JsonArray jsonArray);

	VisitResult visit(JsonObject jsonObject);

	void event(VisitEvent event, JsonObject jsonObject);

	VisitResult visit(JsonNumber jsonNumber);

	void event(VisitEvent event, JsonNumber jsonNumber);

	VisitResult visit(JsonBoolean jsonBoolean);

	void event(VisitEvent event, JsonBoolean jsonBoolean);

	VisitResult visit(JsonObjectProperty jsonObjectProperty);

	void event(VisitEvent event, JsonObjectProperty jsonObjectProperty);

}
