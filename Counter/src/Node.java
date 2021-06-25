public class Node {
      private int name;
      private int label;
	  boolean marked;
	  private Node[] neighbours;  // Neightbours of this node
	  private Edge[] incident;    // Edges incident on this node
	  
	  public Node(int newName) {
		    name = newName;
		    marked = false;
	  }
	  
	  public Node(int newLabel, int newName) {
		    label = newLabel;
			marked = false;
			name = newName;
	  }
	  
	  public void mark() {
	        marked = true;
	  }
	  
	  public void unmark() {
		    marked = false;
	  }
	  
	  public boolean isMarked() {
	        return (marked == true);
	  }
	  
	  public int getLabel() {
		    return label;
	  }
	  
	  public Node[] getNeighbours() {
	        return neighbours;
	  }
	  
	  public Edge[] incidentEdges() {
	        return incident;
	  }
	  
	  public void addNodes(Node[] newNeighbours) {
		    neighbours = newNeighbours;
	  }
	  
	  public void addEdges(Edge[] newIncident) {
		    incident = newIncident;
	  }
	  
	  public int getName() {
		    return name;
	  }
	  
	  public int degree() {
		    return incident.length;
	  }
}
	  
	  