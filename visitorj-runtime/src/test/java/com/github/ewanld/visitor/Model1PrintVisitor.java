package com.github.ewanld.visitor;

public class Model1PrintVisitor implements Model1Visitor {

	@Override
	public VisitResult visit(Model1 model1, String identifier) {
		System.out.println("Visiting Model1, identifier=" + identifier);
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult visit(Model2 model2, String identifier) {
		System.out.println("Visiting Model2, identifier=" + identifier);
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult visit(Model3 model3, String identifier) {
		System.out.println("Visiting Model3, identifier=" + identifier);
		return VisitResult.CONTINUE;
	}

}
