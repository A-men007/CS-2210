import java.util.*;

public class RTable {

    /* Routing table will be stored in the following two lists */
	private ArrayList<String> nextHop;
	private ArrayList<String> destination;
	private int numEntries;
	
	public RTable() {
		nextHop = new ArrayList<String>();
		destination = new ArrayList<String>();
		numEntries = 0;
	}
	
	/* Adds to the current routing table an entry with the given next hop and destination */
	public void addEntry(String hop, String dest) {
		nextHop.add(hop);
		destination.add(dest);
		++numEntries;
	}
	
	public int getNumEntries() {
		return numEntries;
	}
	
	public String getHop(int index) {
		return nextHop.get(index);
	}
	
	public String getDestination(int index) {
		return destination.get(index);
	}
	
	public void printTable() {
		System.out.println("Next Hop      Destination");
		System.out.println("-------------------------");
		for (int i = 0; i < numEntries; ++i) 
			System.out.println("   "+nextHop.get(i)+"             "+destination.get(i));
	}
}