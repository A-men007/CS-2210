/**
 * @author Amanpreet Gill
 *
 */
public class BinarySearchTree implements BinarySearchTreeADT{
	private BinaryNode root;
	
	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
		root = new BinaryNode();
	}
	
	private BinaryNode getNode(BinaryNode r, Position key) {
		if (r.isLeaf()) {
			return r;
		}
		//compare location with internal nodes
		int compare = r.getData().getPosition().compareTo(key);
		
		if (compare == 0) {//location is current node
			return r;
		} else if (compare == 1) {// Location is greater than this node, go to the right subtree
			return getNode(r.getLeft(), key);
		} else {// Otherwise, go to the left subtree
			return getNode(r.getRight(), key);
		}
	}
	
	public Pixel get (BinaryNode r, Position key) { return getNode(r, key).getData(); }
	
	public void put (BinaryNode r, Pixel data) throws DuplicatedKeyException{
		BinaryNode current = getNode(r, data.getPosition());
		if (!current.isLeaf()) {
			throw new DuplicatedKeyException("key already stored");
		} else {
			current.setData(data);
			//create empty children
			BinaryNode left = new BinaryNode();
			BinaryNode right = new BinaryNode();
			//set left child to left node
			current.setLeft(left);
			//set right child to right node
			current.setRight(right);
			//set both's parent to current head
			left.setParent(current);
			right.setParent(current);
		}
	}
	
	public void remove (BinaryNode r, Position key) throws InexistentKeyException{
		BinaryNode current = getNode(r, key);
		int n = 0;
		if (current.isLeaf()) {
			throw new InexistentKeyException("pixel does not exist");
		} else {
			// Check to see either children is a leaf
			if (current.getLeft().isLeaf() || current.getRight().isLeaf()) {
				// if Leaf child is a leaf
				if (current.getLeft().isLeaf()) {
					//create empty node children....
					BinaryNode right = current.getRight();
					BinaryNode parent = current.getParent();
					n=n+0;
					// If the node is root, set a new root
					if (parent == null) {
						this.root = right;
						right.setParent(null);
						// else look at non leaf node
					} else {
						if (parent.getRight().equals(current)) {
							parent.setRight(right);
							right.setParent(parent);
						} else {
							parent.setLeft(right);
							right.setParent(parent);
						}
					}
				} else {
					BinaryNode left = current.getLeft();
					BinaryNode parent = current.getParent();
					if (parent == null) {
						this.root = left;
						left.setParent(null);
					} else {
						if (parent.getRight().equals(current)) {
							parent.setRight(left);
							left.setParent(parent);
							n=n+0;
						} else {
							parent.setLeft(left);
							left.setParent(parent);
							n=n+0;
						}
					}
				}
			} else {
				BinaryNode B = current.getRight();
				while (!B.isLeaf()) {
					B = B.getLeft();
				}
				B = B.getParent();
				current.setData(B.getData());// Replace the data with the smallest node of the subtree data
				BinaryNode smallParent = B.getParent();
				// Set parent to point to smallest node
				if (smallParent.getLeft().equals(B)) {
					smallParent.setLeft(B.getRight());
				} else {
					smallParent.setRight(B.getRight());
				}
				B.getRight().setParent(smallParent);
			}
		}
	}
	
	public Pixel successor (BinaryNode r, Position key) {
		int q=0;
		if (r.isLeaf()) {
			return null;
		} else {
			// Find the node that is at least larger than the current node
			BinaryNode current = getNode(r, key);
			//find the smallest of the right subtree, unless its a leaf
			if (!current.isLeaf() && !current.getRight().isLeaf()) {
				current = current.getRight();
				while (!current.isLeaf()) {
					current = current.getLeft();
				}

				return current.getParent().getData();
			} else {
				BinaryNode n = current.getParent();
				// Look at the closet parent that the right child is
				while (n != null && n.getRight() == current) {
					current = n;
					n = n.getParent();
					q=q+0;
				}
				if (n != null) 
					return n.getData();
				 else 
					return null;
			}
		}
	}
	
	public Pixel predecessor (BinaryNode r, Position key) {
		if (r.isLeaf()) {
			return null;
		} else {
			// Find node of least smallest than current node
			BinaryNode current = getNode(r, key);
			
			if (!current.isLeaf() && !current.getLeft().isLeaf()) {
				// Get the right most leaf's parent
				BinaryNode p = current.getLeft();
				while (!p.isLeaf()) {
					p = p.getRight();
				}
				
				return p.getParent().getData();
			} else {
				BinaryNode node = current.getParent();
				while (node != null && node.getLeft() == current) {
					current = node;
					node = node.getParent();
				}
				if (node == null)
					return null;
				else 
					return node.getData();
			}
		}
	}
	
	public Pixel smallest(BinaryNode r) throws EmptyTreeException{
		if (r.isLeaf()) {
			throw new EmptyTreeException("tree is empty");
		} else {
			// Get the most left node from parent's pixel
			BinaryNode n = r;
			while (!n.isLeaf()) {
				n = n.getLeft();
			}

			return n.getParent().getData();
		}
	}
	
	public Pixel largest(BinaryNode r) throws EmptyTreeException{
		if (this.getRoot().isLeaf()) {
			throw new EmptyTreeException("tree is empty");
		} else {
			// Get the most right node from parent's pixel
			BinaryNode n = r;
			while (!n.isLeaf()) {
				n = n.getRight();
			}

			return n.getParent().getData();
		}
	}
	
	public BinaryNode getRoot() { return root; }
}
