package com.github.ewanld.visitor;

import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;
import com.github.visitorj.Visitable;

public class Model2 implements Visitable<Model1Visitor> {

	@Override
	public void event(VisitEvent event, Model1Visitor visitor) {
		visitor.event(event, this);
	}

	@Override
	public VisitResult visit(Model1Visitor visitor, String identifier) {
		return visitor.visit(this, identifier);
	}

}
