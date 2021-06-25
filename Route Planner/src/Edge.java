public class Edge {
    private Node u, v;
    private String busLine;

    public Edge(Node u, Node v, String busLine) {
    	this.v = v; this.u = u;
        this.busLine = busLine;
    }

    public Node firstEndPoint() {
        return this.u;
    }

    public Node secondEndPoint() {
        return this.v;
    }

    public String getBusLine() {
        return this.busLine;
    }

}
