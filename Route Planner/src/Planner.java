import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.IOException;

public class Planner {
	private Graph BusLines = null;
	private int a, b, c;
	private int begin;
	private int Dest;
	private String current_Line;
	private String previous_Line;
	
	Stack<Node> Path = new Stack<Node>();
	//main constructor
	public Planner(String inputFile) throws MapException, GraphException{
		int N = 0;
		String line;
		String holder;

		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			StringTokenizer st = new StringTokenizer(input.readLine());

			Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			BusLines = new Graph(a*b);

			for(int i=0; i<(2*b)-1; i++) {
				line = input.readLine();
				for(int j=0; j<(2*a)-1; j++) {
					holder = line;
					if(holder == "S") {
						begin = N;
						N++;
					}
					else if(holder == "D") {
						Dest = N;
						N ++;
					}
					else if(holder == "+") {
						N++;
					}
					else {
						if((i % 2)== 0) {
							BusLines.addEdge(BusLines.getNode(N-1), BusLines.getNode(N), holder);
						}
						else if((j%2) == 0){
							BusLines.addEdge(BusLines.getNode(N-a+((j+1)/2)), BusLines.getNode(N+((j+1)/2)), holder);
						}

					}
				}
			}
			input.close();

		}
		catch (IOException e){
			throw new MapException("Error: input file does not exist");
		}


	}

	Graph getGraph() throws MapException {
		if (BusLines == null) {
			throw new MapException("Error: cannot retrieve the map");
		}
		else {
			return BusLines;
		}
	}
	
    public Iterator planTrip() throws GraphException{
    	current_Line = BusLines.getEdge(BusLines.getNode(begin), BusLines.getNode(begin+1)).getBusLine();
        Iterator<Node> route=tripHelper(BusLines.getNode(begin), BusLines.getNode(Dest),0);
        return route;
    }

	/**
	 * helper method
	 */
	private Iterator<Node> tripHelper(Node current, Node Dest, int Change) throws GraphException {
		Iterator<Node> trip = Path.iterator();
		Iterator<Edge> incidents = BusLines.incidentEdges(current);
		current.setMark(true);
		if(Path.isEmpty() || Path.peek() != current) {
			Path.push(current);
		}
		previous_Line = current_Line;
		if(current.getName() == Dest.getName()) {
			trip = Path.iterator();
			return trip;
		}
		while(incidents.hasNext()) {
			Node next = incidents.next().secondEndPoint();
			current_Line = BusLines.getEdge(current, next).getBusLine();

			if(previous_Line == current_Line && next.getMark() == false) {
				return tripHelper(next, Dest, Change);
			}
			else if((Change+1)<c && next.getMark() == false) {
				Change++;
				return tripHelper(next,Dest,Change);
			}
		}
		current_Line = previous_Line;
		Path.pop();
		return tripHelper(Path.peek(), Dest, Change);
	}
}