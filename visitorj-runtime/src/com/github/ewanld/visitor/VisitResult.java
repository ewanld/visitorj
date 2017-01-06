package com.github.ewanld.visitor;

/**
 * Represent the next step to be taken by a Visitor after visiting a node.
 */
public enum VisitResult {
	/**
	 * The Visitor should continue the normal traversal of the tree.
	 */
	CONTINUE(false, false),
	/**
	 * The Visitor should exit immediatly.
	 */
	ABORT(false, false),
	/**
	 * The Visitor should skip the children of the visited node.
	 */
	SKIP_CHILDREN(true, false),

	/**
	 * The Visitor should skip the siblings of the visited node.
	 */
	SKIP_SIBLINGS(false, true),

	/**
	 * The Visitor should skip the children and siblings of the visited node.
	 */
	SKIP_CHILDREN_AND_SIBLINGS(true, true);

	private final boolean skipChildren;
	private final boolean skipSiblings;

	VisitResult(boolean skipChildren, boolean skipSiblings) {
		this.skipChildren = skipChildren;
		this.skipSiblings = skipSiblings;
	}

	public boolean skipChildren() {
		return skipChildren;
	}

	public boolean skipSiblings() {
		return skipSiblings;
	}
}