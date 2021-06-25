public class BinaryNode {

	// You might not need to use all methods and instance variables provided by this class
 	private int value; 
 	private BinaryNode leftChild, rightChild;
 	
 	// Create a leaf node
 	public BinaryNode(int val) {
 		value = val;
 		leftChild = null;
 		rightChild = null;
 	}
 	
 	// Create an internal node with the given children
 	public BinaryNode(int val, BinaryNode left, BinaryNode right) {
 		value = val;
 		leftChild = left;
 		rightChild = right;
 	}
 	
 	public BinaryNode getLeft() {
 		return leftChild;
 	}
 	
 	public BinaryNode getRight() {
 		return rightChild;
 	}
 
 	public int getValue() {
 		return value;
 	}
 	
 	public boolean isLeaf() {
 		return (leftChild == null) && (rightChild == null);
 	}
 	
 	public void setLeft(BinaryNode left) {
 		leftChild = left;
 	}
 	
 	public void setRight(BinaryNode right) {
 		rightChild = right;
 	}
}
