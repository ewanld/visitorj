package com.github.ewanld.visitorexample.model;


import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.VisitEvent;


public interface JsonVisitor {
	VisitResult visit(JsonArray jsonArray);

	default void event(VisitEvent event, JsonArray jsonArray) {}

	VisitResult visit(JsonObject jsonObject);

	default void event(VisitEvent event, JsonObject jsonObject) {}

	VisitResult visit(JsonNumber jsonNumber);

	default void event(VisitEvent event, JsonNumber jsonNumber) {}

	VisitResult visit(JsonBoolean jsonBoolean);

	default void event(VisitEvent event, JsonBoolean jsonBoolean) {}

	VisitResult visit(JsonObjectProperty jsonObjectProperty);

	default void event(VisitEvent event, JsonObjectProperty jsonObjectProperty) {}

}
