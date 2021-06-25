/**
 * @author AmanpreetG
 */
//implement a class Node storing an object of the class Data to construct
//the linked lists associated to the entries of the hash table
public class Node {
	//Node pointing to the next node in the index
	private Node next;
	//special class for storing data and key
	private Configuration add;
	public Node(Configuration add) {
		this.add = add;
		this.next = null;
	}
	//
	public Configuration getNodeEntry() {
		return this.add;
	}
	public void setNextNode(Node nextNode) {
		this.next = nextNode;
	}
	public Node getNextNode() {
		return this.next;
	}
}	