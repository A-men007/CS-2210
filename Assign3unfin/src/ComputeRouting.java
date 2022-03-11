
public class ComputeRouting extends Algorithm {

    public Object run() {
        // Our sumTree algorithm returns the total of distances from the descendants to this node 
        String result = computeRoutingTable(getID());
        return result;
    }

    public String computeRoutingTable(String id) {
        try {
	        RoutingTable table = new RoutingTable(id);

			/* Your initialization code goes here */
        
            while (waitForNextRound()) {  /* synchronous loop */
                
			/* Your algorithm goes here */
			
            }
        } catch(SimulatorException e){
            System.out.println("ERROR: " + e.toString());
        }
    
        return "";
    }

}