package com.github.ewanld.visitorexample.model;

import java.util.List;
import java.util.ArrayList;

import com.github.ewanld.visitor.VisitResult;
import com.github.ewanld.visitor.VisitEvent;


public abstract class JsonVisitorWithContext implements JsonVisitor {
	protected List<JsonArray> jsonArrayAncestors = new ArrayList<JsonArray>();
	protected List<JsonObject> jsonObjectAncestors = new ArrayList<JsonObject>();
	protected List<JsonNumber> jsonNumberAncestors = new ArrayList<JsonNumber>();
	protected List<JsonBoolean> jsonBooleanAncestors = new ArrayList<JsonBoolean>();
	protected List<JsonObjectProperty> jsonObjectPropertyAncestors = new ArrayList<JsonObjectProperty>();

	public final VisitResult visit(JsonArray jsonArray) {
		this.jsonArrayAncestors.add(jsonArray);
		return onVisit(jsonArray);
	}

	protected abstract VisitResult onVisit(JsonArray jsonArray);

	public final void event(VisitEvent event, JsonArray jsonArray) {
		onEvent(event, jsonArray);
		this.jsonArrayAncestors.remove(jsonArrayAncestors.size() - 1);
	}

	protected abstract void onEvent(VisitEvent event, JsonArray jsonArray);

	protected JsonArray getJsonArray() {
		return jsonArrayAncestors.get(jsonArrayAncestors.size() - 1);
	}

	public final VisitResult visit(JsonObject jsonObject) {
		this.jsonObjectAncestors.add(jsonObject);
		return onVisit(jsonObject);
	}

	protected abstract VisitResult onVisit(JsonObject jsonObject);

	public final void event(VisitEvent event, JsonObject jsonObject) {
		onEvent(event, jsonObject);
		this.jsonObjectAncestors.remove(jsonObjectAncestors.size() - 1);
	}

	protected abstract void onEvent(VisitEvent event, JsonObject jsonObject);

	protected JsonObject getJsonObject() {
		return jsonObjectAncestors.get(jsonObjectAncestors.size() - 1);
	}

	public final VisitResult visit(JsonNumber jsonNumber) {
		this.jsonNumberAncestors.add(jsonNumber);
		return onVisit(jsonNumber);
	}

	protected abstract VisitResult onVisit(JsonNumber jsonNumber);

	public final void event(VisitEvent event, JsonNumber jsonNumber) {
		onEvent(event, jsonNumber);
		this.jsonNumberAncestors.remove(jsonNumberAncestors.size() - 1);
	}

	protected abstract void onEvent(VisitEvent event, JsonNumber jsonNumber);

	protected JsonNumber getJsonNumber() {
		return jsonNumberAncestors.get(jsonNumberAncestors.size() - 1);
	}

	public final VisitResult visit(JsonBoolean jsonBoolean) {
		this.jsonBooleanAncestors.add(jsonBoolean);
		return onVisit(jsonBoolean);
	}

	protected abstract VisitResult onVisit(JsonBoolean jsonBoolean);

	public final void event(VisitEvent event, JsonBoolean jsonBoolean) {
		onEvent(event, jsonBoolean);
		this.jsonBooleanAncestors.remove(jsonBooleanAncestors.size() - 1);
	}

	protected abstract void onEvent(VisitEvent event, JsonBoolean jsonBoolean);

	protected JsonBoolean getJsonBoolean() {
		return jsonBooleanAncestors.get(jsonBooleanAncestors.size() - 1);
	}

	public final VisitResult visit(JsonObjectProperty jsonObjectProperty) {
		this.jsonObjectPropertyAncestors.add(jsonObjectProperty);
		return onVisit(jsonObjectProperty);
	}

	protected abstract VisitResult onVisit(JsonObjectProperty jsonObjectProperty);

	public final void event(VisitEvent event, JsonObjectProperty jsonObjectProperty) {
		onEvent(event, jsonObjectProperty);
		this.jsonObjectPropertyAncestors.remove(jsonObjectPropertyAncestors.size() - 1);
	}

	protected abstract void onEvent(VisitEvent event, JsonObjectProperty jsonObjectProperty);

	protected JsonObjectProperty getJsonObjectProperty() {
		return jsonObjectPropertyAncestors.get(jsonObjectPropertyAncestors.size() - 1);
	}

}
