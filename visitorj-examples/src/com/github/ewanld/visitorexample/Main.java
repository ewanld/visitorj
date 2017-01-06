package com.github.ewanld.visitorexample;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Arrays;

import com.github.ewanld.visitorexample.model.JsonArray;
import com.github.ewanld.visitorexample.model.JsonBoolean;
import com.github.ewanld.visitorexample.model.JsonElement;
import com.github.ewanld.visitorexample.model.JsonNumber;
import com.github.ewanld.visitorexample.model.JsonObject;
import com.github.ewanld.visitorexample.model.JsonObjectProperty;
import com.github.ewanld.visitorexample.visitors.PrintJsonVisitor;

public class Main {
	public static void main(String[] args) throws Exception {
		// @formatter:off
		final JsonElement elt = obj(
			prop("value", num(3)),
			prop("visible", bool(true)),
			prop("myArray", array(
				num(3), num(4), num(5)
			)),
			prop("myDict", obj(
					prop("a", num(10)),
					prop("b", num(20))
			))
		);
		// @formatter:on

		try (Writer outWriter = new OutputStreamWriter(System.out)) {
			elt.accept(new PrintJsonVisitor(outWriter));
		}
	}

	public static JsonObjectProperty prop(String name, JsonElement value) {
		return new JsonObjectProperty(name, value);
	}

	public static JsonObject obj(JsonObjectProperty... properties) {
		return new JsonObject(Arrays.asList(properties));
	}

	public static JsonBoolean bool(boolean b) {
		return new JsonBoolean(b);
	}

	public static JsonNumber num(BigDecimal n) {
		return new JsonNumber(n);
	}

	public static JsonNumber num(long n) {
		return new JsonNumber(BigDecimal.valueOf(n));
	}

	public static JsonArray array(JsonElement... elt) {
		return new JsonArray(Arrays.asList(elt));
	}
}
