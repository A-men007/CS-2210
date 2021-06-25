public class LeftBalanced {

    public boolean isLeftBalanced(BinaryNode r) {
    /* Input: Root r of a proper binary tree
       Output: true if the tree rooted at r is left balanced; false otherwise
       Methods you can use from class BinaryNode
        - getLeft() returns the left child of a node
        - getRight() returns the right child of a node
        - isLeaf() returns true if node is a leaf; false otherwise
        - getKey() returns the int key stored in THIS node.
    */
    	if (r.getValue() == r.getLeft().getValue()) {
    		return true;
    	}
    	else if (isLeftBalanced(r.getLeft())) {
    		return true;
    	}
    	else if (isLeftBalanced(r.getRight())) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}