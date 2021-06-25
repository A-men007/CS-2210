
public class BinaryNode {
	private Pixel value;
	private BinaryNode parent, left, right;
	public BinaryNode (Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	public BinaryNode () {
		value = null;
		parent = null;
		left = null;
		right = null;
	}
	
	public BinaryNode getParent() {
		return parent;
	}
	
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}
	
	public void setLeft (BinaryNode p) {
		this.left = p;
	}
	
	public void setRight (BinaryNode p) {
		this.right = p;
	}
	
	public void setData (Pixel value) {
		this.value = value;
	}
	
	public boolean isLeaf() {
		return value == null;
	}
	
	public Pixel getData() {
		return value;
	}
	
	public BinaryNode getLeft(){
		return left;
	}
	
	public BinaryNode getRight(){
		return right;
	}
}
