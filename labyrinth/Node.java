
public class Node {
	private int name;
	private boolean mark;
	/*the constructor for the class. Creates a node with the given name. The name of a
	node is an integer value between 0 and n − 1*/
	Node(int name) {
		this.name = name;
	}
	void setMark(boolean mark){ // marks the node with the specified value.
		this.mark = mark;
	}	
	boolean getMark() {
		return this.mark;
	}
	int getName() {
		return this.name;
	}
}
