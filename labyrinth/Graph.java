import java.util.Iterator;
import java.util.Stack;

/**
 * @author amanpreet gill
 *
 */
public class Graph implements GraphADT{
	private int numN;		//Variable for number of nodes in the graph
	private Edge adjMat[][];	//Adjacency Matrix object for storing information about which vertices are connected to which, as well as the relationships between them
	private Node nodeArr[];		//Array to store the nodes of the graph
	/**
	 * contructor
	 */
	public Graph(int n) {
		this.numN = n;
		adjMat = new Edge[n][n];
		nodeArr = new Node[n];
		for(int i = 0; i < n; i++){		//From 0 to the number of nodes
			nodeArr[i] = new Node(i);	//place node in current index of the array
		}
	}

	public void insertEdge(Node nodeu, Node nodev, int type) throws GraphException {
		// TODO Auto-generated method stub
		if(inGraph(nodeu, nodev)){		//If the passed nodes exist within the graph, do the following
			Edge e = adjMat[nodeu.getName()][nodev.getName()];	//Get the element stored between these two nodes (will return an edge or null)
			if(e == null){														//If the element was null, we can add the edge
				adjMat[nodeu.getName()][nodev.getName()] = new Edge(nodeu, nodev, type);		//Create an edge between the first and second with the passed data
				adjMat[nodev.getName()][nodeu.getName()] = new Edge(nodev, nodeu, type);		//Create an edge between the second and first with the passed data (accounts for both directions)
			}
			else	//Otherwise there is already an edge between the two nodes
				throw new GraphException("Node already exists within graph");	//Throw an exception
		}
		else	//If the nodes are not in the graph, throw an exception
			throw new GraphException("Nodes do not exist within graph");
		
	}
	
	@Override
	public void insertEdge(Node nodeu, Node nodev, int type, String label) throws GraphException {
		if(inGraph(nodeu, nodev)){		//If the passed nodes exist within the graph, do the following
			Edge e = adjMat[nodeu.getName()][nodev.getName()];	//Get the element stored between these two nodes (will return an edge or null)
			if(e == null){														//If the element was null, we can add the edge
				adjMat[nodeu.getName()][nodev.getName()] = new Edge(nodeu, nodev, type);		//Create an edge between the first and second with the passed data
				adjMat[nodev.getName()][nodeu.getName()] = new Edge(nodev, nodeu, type);		//Create an edge between the second and first with the passed data (accounts for both directions)
			}
			else	//Otherwise there is already an edge between the two nodes
				throw new GraphException("Node already exists within graph");	//Throw an exception
		}
		else	//If the nodes are not in the graph, throw an exception
			throw new GraphException("Nodes do not exist within graph");
	}

	@Override
	public Node getNode(int u) throws GraphException {
		if((u < 0) || (u >= numN))	//If the name of this node is not within range of the nodes in this graph
			throw new GraphException("Node not found in graph");	//Throw an exception
		else	//Otherwise return the corresponding node
			return nodeArr[u];
	}

	@Override
	public Iterator incidentEdges(Node u) throws GraphException {
		// TODO Auto-generated method stub
		if(inGraph(u)){	//If the node is in the graph
			Stack p = new Stack();		//Create a stack object
			for(int i = 0; i < numN; i++){		//Loop through the row that corresponds to this node
				if(adjMat[u.getName()][i] != null)		//If the element at the current index is an edge (a connection to another node)
					p.push(adjMat[u.getName()][i]);		//Add it to the stack
			}
			
			return p.iterator();	//Return the iterator of this stack
			
		}
		else	//Otherwise the node is not in the graph, so throw an exception
			throw new GraphException("Node not in graph");
	}

	@Override
	public Edge getEdge(Node u, Node v) throws GraphException {
		// TODO Auto-generated method stub
		if(inGraph(u, v)){	//If both nodes are in the graph
			Edge result = adjMat[u.getName()][v.getName()];	//get the edge between these two nodes
	
			if(result == null)	//If we got null, there is not edge between these two so throw an exception
				throw new GraphException("Edge not found in graph");
			else
				return result;
		}
		else	//If the nodes are not in the graph, throw an exception
			throw new GraphException("Input nodes do not exist in graph");
	}

	@Override
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		// TODO Auto-generated method stub
		if(inGraph(u, v)){	//If both nodes are in the graph
			Edge result = adjMat[u.getName()][v.getName()];	//Get the edge between the nodes
			if(result == null)	//If we got null, they are not connected to return false
				return false;
			else	//Otherwise we got an edge, so return true
				return true;
		}
		else	//If the nodes are not in the graph, throw an exception
			throw new GraphException("Nodes not in graph");
	}

	//Method to determine if two nodes exist within a graph
	private boolean inGraph(Node u, Node v){
		try{	//Try to fetch the two nodes
			getNode(u.getName());
			getNode(v.getName());
			return true;			//If no exception was thrown while fetching the nodes they are in the graph
		}
		catch(GraphException e){
			return false;	//Otherwise an exception was thrown because one of them is not in the graph, so return false
		}
	}
	// method for one node parameter
	private boolean inGraph(Node u){
		try{
			getNode(u.getName());
			return true;
		}
		catch(GraphException e){
			return false;
		}
	}	
}
