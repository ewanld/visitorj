package com.github.ewanld.visitor;

import java.util.List;

public class Model1 implements Visitable<Model1Visitor> {
	public Model2 model2;
	public List<Model2> model2List;
	public Model3 model3;
	private final VisitableList<Model1Visitor> visitableChildren = new VisitableList<>();

	public Model1(Model2 model2, List<Model2> model2List, Model3 model3) {
		this.model2 = model2;
		this.model2List = model2List;
		this.model3 = model3;
		visitableChildren.add(model2, "model2");
		visitableChildren.add(model2List, "model2List");
		visitableChildren.add(model3);
	}

	@Override
	public VisitableList<Model1Visitor> getVisitableChildren() {
		return visitableChildren;
	}

	@Override
	public void event(VisitEvent event, Model1Visitor visitor) {
		visitor.event(event, this);
	}

	@Override
	public VisitResult visit(Model1Visitor visitor, String identifier) {
		return visitor.visit(this, identifier);
	}

}