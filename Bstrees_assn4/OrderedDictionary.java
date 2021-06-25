/**
 * @author Amanpreet
 * this class implements the dictionary bst and stores all the words along with their definitions.
 * */
public class OrderedDictionary implements OrderedDictionaryADT {
	// declare variables
	private Node<DataItem> root;

	public OrderedDictionary(){
		root = new Node<DataItem>();
	}
	
	/* Returns the DataItem object with key k, or it returns null if such a DataItem
	is not in the dictionary. */
	public DataItem get (Key k) {
		Node<DataItem> curr = root;
		
		while ((curr.hasChildren()) && (curr.getElement().getKey().compareTo(k) != 0)) {
			if (curr.getElement().getKey().compareTo(k) < 0) {
				curr = curr.getRightChild();
			} else {
				curr = curr.getLeftChild();
			}
		}
		return curr.getElement();
	}
	
	/* Inserts d into the ordered dictionary. It throws a DictionaryException if a
	DataItem with the same Key as d is already in the dictionary. */
	public void put (DataItem d) throws DictionaryException {
		Node<DataItem> curr = root;
		Node<DataItem> parent = new Node<DataItem>();
		Node<DataItem> lChild = new Node<DataItem>();
		Node<DataItem> rChild = new Node<DataItem>();

		if (root.getElement() == null) {
			root.setElement(d);
			root.setLeftChild(lChild);
			root.setRightChild(rChild);
		} else if (get(d.getKey()) != null) {
			throw new DictionaryException("Could not put key; already exists.");
		} else {
			while (curr.hasChildren()) {
				parent = curr;
				if (curr.getElement().getKey().compareTo(d.getKey()) < 0) {
					curr = curr.getRightChild();
				} else {
					curr = curr.getLeftChild();
				}
			}
			curr.setElement(d);
			curr.setParent(parent);
			curr.setLeftChild(lChild);
			curr.setRightChild(rChild);
		}
	}
	
	/* Removes the DataItem with Key k from the dictionary. It throws a
	DictionaryException if the DataItem is not in the dictionary. */
	public void remove (Key k) throws DictionaryException {
		Node<DataItem> current = root;
		Node<DataItem> parent = current;
		Node<DataItem> rem;
		Node<DataItem> child = new Node<DataItem>();
		Node<DataItem> small = new Node<DataItem>();
		Node<DataItem> smallParent;

		rem = getNode(k);
		current = rem;
		parent = rem.getParent();

		if (rem.isLeaf()) {
			throw new DictionaryException("Error; entry not in dictionary.");
		} else {
			if ((rem.getLeftChild().isLeaf()) || (rem.getRightChild().isLeaf())) {
				if (rem.getLeftChild().isLeaf()) {
					child = rem.getRightChild();
				} else {
					child = rem.getLeftChild();
				}
				if (rem.getParent() == null) {
					child.setLeftChild(root.getLeftChild());
					child.setRightChild(root.getRightChild());
					root.setElement(child.getElement());
					return;
				} else if ((parent.getRightChild().getElement() != null) && (parent.getRightChild().getElement()
						.getKey().compareTo(current.getElement().getKey()) == 0)) {
					parent.setRightChild(child);
				} else {
					parent.setLeftChild(child);
				}
			} else {
				small = small(rem.getRightChild());
				small.setRightChild(rem.getRightChild());
				rem.getRightChild().setParent(small);
				small.setLeftChild(rem.getLeftChild());
				rem.getLeftChild().setParent(small);
				smallParent = small.getParent();
				if (parent == null) {
					root.setElement(small.getElement());
				} else if (parent.getLeftChild().getElement().getKey().compareTo(rem.getElement().getKey()) == 0) {
					parent.setLeftChild(small);
				} else {
					parent.setRightChild(small);
				}
				if (smallParent.getLeftChild().getElement().getKey().compareTo(small.getElement().getKey()) == 0) {
					smallParent.setLeftChild(new Node<DataItem>());
				}
			}
		}
	}
	
	/* Returns the successor of k (the DataItem from the ordered dictionary with
	smallest Key larger than k); it returns null if the given Key has no
	successor. The given Key DOES NOT need to be in the dictionary. */
	public DataItem successor (Key k) {
		Node<DataItem> curr = root;
		Node<DataItem> parent = new Node<DataItem>();
		Node<DataItem> prev;

		if (curr.isLeaf()) {
			return null;
		}

		curr = getNode(k);

		if (curr.getElement() == null) {
			curr = root;
			prev = curr;
			while ((curr.hasChildren()) && (curr.getElement().getKey().compareTo(k) != 0)) {
				if (curr.getElement().getKey().compareTo(k) < 0) {
					prev = curr;
					curr = curr.getRightChild();
				} else {
					prev = curr;
					curr = curr.getLeftChild();
				}

			}
			curr = prev;
			if ((curr.getElement().getKey().compareTo(k) > 0) && (curr.getRightChild().getElement() == null)) {
				return curr.getElement();
			}
		}

		if (!curr.getRightChild().isLeaf()) {
			return smallest(curr.getRightChild());
		} else {
			parent = curr.getParent();
			while ((parent.getParent() != null) && (curr.isRightChild())) {
				curr = parent;
				parent = parent.getParent();
			}
			if ((parent == root) && (curr.isRightChild())) {
				return null;
			} else {
				return parent.getElement();
			}
		}
	}
	
	/* Returns the predecessor of k (the DataItem from the ordered dictionary with
	largest key smaller than k; it returns null if the given Key has no
	predecessor */
	public DataItem predecessor (Key k) {
		Node<DataItem> curr = root;
		Node<DataItem> parent = new Node<DataItem>();
		Node<DataItem> prev;

		if (curr.isLeaf()) {
			return null;
		}
		curr = getNode(k);
		if (curr.getElement() == null) {
			curr = root;
			prev = curr;
			while ((curr.hasChildren()) && (curr.getElement().getKey().compareTo(k) != 0)) {
				if (curr.getElement().getKey().compareTo(k) < 0) {
					prev = curr;
					curr = curr.getRightChild();
				} else {
					prev = curr;
					curr = curr.getLeftChild();
				}
			}
			curr = prev;
			if ((curr.getElement().getKey().compareTo(k) < 0) && (curr.getRightChild().getElement() == null)) {
				return curr.getElement();
			}
		}
		if (!curr.getLeftChild().isLeaf()) {
			return large(curr.getLeftChild());
		} else {
			parent = curr.getParent();
			while ((parent.getParent() != null) && (curr.isLeftChild())) {
				curr = parent;
				parent = parent.getParent();
			}
			if ((parent == root) && (curr.isLeftChild())) {
				return null;
			} else {
				return parent.getElement();
			}
		}
	}
	/* Returns the DataItem with smallest key in the ordered dictionary. Returns
	null if the dictionary is empty. */
	public DataItem smallest () {
		Node<DataItem> curr = root;
		Node<DataItem> previous = curr;

		if (root.getElement() == null) {
			return null;
		} else {
			while ((curr.hasChildren()) && (curr.getElement() != null)) {
				previous = curr;
				curr = curr.getLeftChild();
			}
			return previous.getElement();
		}
	}
	/* Returns the DataItem with largest key in the ordered dictionary. Returns
	null if the dictionary is empty. */
	public DataItem largest () {
		Node<DataItem> curr = root;
		Node<DataItem> previous = curr;

		if (root.getElement() == null) {
			return null;
		} else {
			while ((curr.hasChildren()) && (curr.getElement() != null)) {
				previous = curr;
				curr = curr.getRightChild();
			}
			return previous.getElement();
		}
	}
	
	private DataItem smallest(Node<DataItem> r) {
		Node<DataItem> curr = r;
		Node<DataItem> previous = curr;

		if (root.getElement() == null) {
			return null;
		} else {
			while ((curr.hasChildren()) && (!curr.isLeaf())) {
				previous = curr;
				curr = curr.getLeftChild();
			}
			return previous.getElement();
		}
	}
	
	private Node<DataItem> small(Node<DataItem> r) {
		Node<DataItem> curr = r;
		Node<DataItem> previous = curr;

		if (root.getElement() == null) {
			return null;
		} else {
			while ((curr.hasChildren()) && (!curr.isLeaf())) {
				previous = curr;
				curr = curr.getLeftChild();
			}
			return previous;
		}
	}
	
	private DataItem large(Node<DataItem> r) {
		Node<DataItem> curr = r;
		Node<DataItem> previous = curr;

		if (root.getElement() == null) {
			return null;
		} else {
			while ((curr.hasChildren()) && (curr.getElement() != null)) {
				previous = curr;
				curr = curr.getRightChild();
			}
			return previous.getElement();
		}
	}
	
	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Node<DataItem> node) {
		if (node == null) {
			return;
		}
	}


	/**
	 * Helper method to return the Node that contains the key
	 * @param k to find in the bst
	 * @return find the node that matches the k or null if it could not be found.
	 */
	private Node<DataItem> getNode(Key k) {
		Node<DataItem> curr = root;

		// if the tree is not empty
        if(curr != null){
            while (curr.getElement() != null) {

                if (curr.getElement().getKey().compareTo(k) == 0) {
                    return curr;
                }

                else if (curr.getElement().getKey().compareTo(k) < 0) {
                	curr = curr.getRightChild();
                }

                else{
                	curr = curr.getLeftChild();
                }
            }
        }

        // returns the found node or null if not found
        return curr;
	}

}
