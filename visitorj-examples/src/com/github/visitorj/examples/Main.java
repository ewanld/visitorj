package com.github.visitorj.examples;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Arrays;

import com.github.visitorj.examples.model.JsonArray;
import com.github.visitorj.examples.model.JsonBoolean;
import com.github.visitorj.examples.model.JsonElement;
import com.github.visitorj.examples.model.JsonNumber;
import com.github.visitorj.examples.model.JsonObject;
import com.github.visitorj.examples.model.JsonObjectProperty;
import com.github.visitorj.examples.visitors.FindByIdVisitor;
import com.github.visitorj.examples.visitors.PrintJsonVisitor;

public class Main {
	public static void main(String[] args) throws Exception {
		// @formatter:off
		final JsonElement elt = array(
			obj(
				prop("id", num(1)),
				prop("visible", bool(true)),
				prop("contents", array(
					num(3), num(4), num(5)
				))
			), obj(
				prop("id", num(2)),
				prop("visible", bool(false)),
				prop("contents", array(
					num(6), num(7), num(8)
				))
			), obj(
				prop("id", num(3)),
				prop("visible", bool(true)),
				prop("contents", array(
					num(9), num(10), num(11)
				))
			)
		);
		// @formatter:on

		try (Writer outWriter = new OutputStreamWriter(System.out)) {
			// serialize the whole JSON element
			elt.accept(new PrintJsonVisitor(outWriter));
			outWriter.write("\n");
			outWriter.flush();

			// find the object with id == 2
			final FindByIdVisitor findId2 = new FindByIdVisitor(2);
			elt.accept(findId2);

			// serialize the object with id ==2
			findId2.getResult().accept(new PrintJsonVisitor(outWriter));
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
