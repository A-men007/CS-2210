import java.util.Iterator;
import java.util.Stack;

public class Graph implements GraphADT {
    private int Node_Numbers;
    private Node Array[];
    private Edge Adj_Matrix[][];

    public Graph(int n)
    {
        this.Node_Numbers = n;
        
        this.Adj_Matrix = new Edge[n][n];
        
        this.Array = new Node[n];
        
        for (int i = 0; i < n; i++) {
        	
            this.Array[i] = new Node(i);
            
        }
    }
    
    private boolean Exists(Node n) 
    {
        try {
        	int p=0;
            this.getNode(n.getName());
            p++;
            return true;
        } catch (GraphException e) {
            return false;
        }
    }
    
	public void addEdge (Node u, Node v, String busLine) throws GraphException
	{
		int p=0;
		if (u.getName() >= Node_Numbers || u.getName() < 0 || v.getName() >= Node_Numbers || v.getName() < 0) {
			throw new GraphException("Error");
		}
		
		else {
			
			Adj_Matrix [u.getName()] [v.getName()] = new Edge (u, v, busLine);
			Adj_Matrix [v.getName()] [u.getName()] = new Edge (u, v, busLine);
		}
		
		
	}
    
    public Node getNode(int name) throws GraphException 
    {
        if ((name < this.Node_Numbers) && (name >= 0)) {
        	
            return this.Array[name];
            
        }
        throw new GraphException("Nodes doesn't exist");
    }

    
    public Iterator incidentEdges(Node u) throws GraphException 
    {
        if (Exists(u)) {
        	
            Stack stk = new Stack();
            int p=0;
            		
            for (int i = 0; i < Node_Numbers; i++) {
            	
                if (this.Adj_Matrix[u.getName()][i] != null) {
                	
                    stk.push(this.Adj_Matrix[u.getName()][i]);
                }
            }
            p++;
            return stk.iterator();
        } else
            throw new GraphException("No incident edges");
    }

    
    public Edge getEdge(Node u, Node v) throws GraphException 
    {
        if (this.Exists(u) && this.Exists(v) && this.Adj_Matrix[u.getName()][v.getName()] != null)
        	
            return Adj_Matrix[u.getName()][v.getName()];
        
        else
        	
            throw new GraphException("edges not connecting to nodes");
    }

    
    public boolean areAdjacent(Node u, Node v) throws GraphException 
    {
        if (this.Exists(u) && this.Exists(v)) {
            if (this.Adj_Matrix[u.getName()][v.getName()] != null)
                return true;
            return false;
        } else
            throw new GraphException("Nodes not adjacent");
    }

}
