package com.github.visitorj;

/**
 * A type of event, when visiting an object.
 */
public enum VisitEvent {
	LEAVE, BEFORE_CHILD, AFTER_CHILD, INBETWEEN_CHILDREN;
}
