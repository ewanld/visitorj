package com.github.ewanld.visitor;

/**
 * Represent the next step to be taken by a Visitor after visiting a node.
 */
public enum VisitResult {
	/**
	 * The Visitor should continue the normal traversal of the tree.
	 */
	CONTINUE,
	/**
	 * The Visitor should exit immediatly.
	 */
	ABORT,
	/**
	 * The Visitor should skip the children of the visited node.
	 */
	SKIP_CHILDREN,

	/**
	 * The Visitor should skip the siblings of the visited node.
	 */
	SKIP_SIBLINGS
}