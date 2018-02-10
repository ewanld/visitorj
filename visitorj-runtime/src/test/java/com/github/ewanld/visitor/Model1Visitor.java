package com.github.ewanld.visitor;

import com.github.visitorj.VisitEvent;
import com.github.visitorj.VisitResult;

public interface Model1Visitor {
	VisitResult visit(Model1 model1, String identifier);

	default void event(VisitEvent event, Model1 model1) {
	}

	VisitResult visit(Model2 model2, String identifier);

	default void event(VisitEvent event, Model2 model2) {
	}

	VisitResult visit(Model3 model3, String identifier);

	default void event(VisitEvent event, Model3 model3) {
	}
}
