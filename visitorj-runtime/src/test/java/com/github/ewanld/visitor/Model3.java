package com.github.ewanld.visitor;

public class Model3 implements Visitable<Model1Visitor> {
	@Override
	public void event(VisitEvent event, Model1Visitor visitor) {
		visitor.event(event, this);
	}

	@Override
	public VisitResult visit(Model1Visitor visitor, String identifier) {
		return visitor.visit(this, identifier);
	}
}
