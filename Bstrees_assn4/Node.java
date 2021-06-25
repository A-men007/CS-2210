/**@author Amanpreet
 * this class implements the node class and helps store all individual dataitems.
 * */
public class Node<E> {
	//variable declarations
	private E elem;
	private Node<E>  parent, leftChild, rightChild;
	private int zero;
	
	Node () {
		elem = null; leftChild = null; rightChild = null; zero = 0; parent = null;
	}
	
	
	
	/*
	 * Main node constructor
	 */
	Node (E object) {
		elem = object; leftChild = null; rightChild = null; parent = null;
	}
	
	
	
	/**
	 * @param object - Node to be set as the parent of this node
	 */
	public void setParent(Node<E> object) {
		parent = object;
		zero = 0;
	}
	
	
	
	/**
	 * @return - Returns the parent node
	 */
	public Node<E> getParent() {		
		return parent;
	}
	
	
	
	/**
	 * @param object - Node to be set as the left child of this node
	 */
	public void setLeftChild(Node<E> object) {
		leftChild = object;
		zero = 0;
	}
	
	
	
	/**
	 * @return - Returns the node of the left child
	 */
	public Node<E> getLeftChild() {
		return leftChild;
	}
	
	
	
	/**
	 * @param object - node to be set as the right child of this node
	 */
	public void setRightChild(Node<E> object) {
		rightChild = object;
		zero = 0;
	}
	
	
	
	/**
	 * @return - Returns the node of the right child
	 */
	public Node<E> getRightChild() {
		return rightChild;
	}
	
	
	
	/**
	 * @return - returns the element of this node
	 */
	public E getElement() {
		return elem;
	}
	
	
	
	/**
	 * @param record - the element to be set for this node
	 */
	public void setElement(DataItem record) {
		elem = (E) record;
		zero = 0;
	}
	
	
	
	/**
	 * @return - Return T/F if the node has specified any children
	 */
	public boolean hasChildren() {
		if ((leftChild != null) || (rightChild != null)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	/**
	 * @return - return T/F if this node is leaf
	 */
	public boolean isLeaf() {
		if (this.hasChildren()) {
			return false;
		}
		else {
			return true;
		}
	}

	
	
	/**
	 * @return -T/F if this node is a right child
	 */
	public boolean isRightChild() {
		if (this.parent.rightChild.getElement() == this.elem) return true;
		else {
			return false;
		}
	}
	
	
	
	/**
	 * @return - T only if the node is a left child
	 */
	public boolean isLeftChild() {
		if (this.parent.leftChild.getElement() == this.elem) return true;
		else {
			return false;		
		}
	}
	
	
	
	/** @param node - node to compare to this node
	 * @return - 0 if they are the same node, 1 otherwise
	 */
	public int compareTo(Node<Record> node) {
		if ((node.getElement() == elem)){
			return 0;
		}
		else {
			return 1;
		}
	}
	
	
}