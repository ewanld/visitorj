package com.github.ewanld.visitorexample.model;

import com.github.ewanld.visitor.Visitable;
import com.github.ewanld.visitorexample.model.JsonVisitor;

public interface JsonElement extends Visitable<JsonVisitor> {

}
