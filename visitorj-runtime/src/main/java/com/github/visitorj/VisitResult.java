package com.github.visitorj;

/**
 * Represent the next step to be taken by a Visitor after visiting a node.
 */
public enum VisitResult {
	/**
	 * The Visitor should continue the normal traversal of the tree.
	 */
	CONTINUE(false, false, false),
	/**
	 * The Visitor should exit immediatly.
	 */
	ABORT(false, false, false),
	/**
	 * The Visitor should skip the visited node, including children.
	 * <p>
	 * The difference with {@code SKIP_CHILDREN} is that a {@code leave} event will NOT be created for the visited
	 * node.
	 */
	SKIP_NODE(true, true, false),
	/**
	 * The Visitor should skip the children of the visited node.
	 * <p>
	 * The difference with {@code SKIP_NODE} is that a {@code leave} event will be created for the visited
	 * node.
	 */
	SKIP_CHILDREN(false, true, false),

	/**
	 * The Visitor should skip the siblings of the visited node.
	 */
	SKIP_SIBLINGS(false, false, true),

	/**
	 * The Visitor should skip the children and siblings of the visited node.
	 */
	SKIP_CHILDREN_AND_SIBLINGS(false, true, true),

	/**
	 * The Visitor should skip the current node, its children, and its siblings.
	 */
	SKIP_NODE_AND_SIBLINGS(true, true, true),;

	private final boolean skipCurrentNode;
	private final boolean skipChildren;
	private final boolean skipSiblings;

	VisitResult(boolean skipCurrentNode, boolean skipChildren, boolean skipSiblings) {
		this.skipCurrentNode = skipCurrentNode;
		this.skipChildren = skipChildren;
		this.skipSiblings = skipSiblings;
	}

	public boolean skipChildren() {
		return skipChildren;
	}

	public boolean skipSiblings() {
		return skipSiblings;
	}

	public boolean skipCurrentNode() {
		return skipCurrentNode;
	}
}