import java.util.Vector; //We need this for the Vector class.

public class DBFS extends Algorithm {
	static int server_Port = 5553;
    static int vector_time[] = {0, 0, 0};
    static int balance = 1000;
    static HashMap<String, Integer> server_map = new HashMap<String, Integer>();
    
    public Object run() {
    	ServerSocket serverSocket = null;
        try {
            VectorClockThreads obj = new VectorClockThreads();

            while (true) {

                if (operation == "sender") {
                    Thread.sleep(5000);
                    System.out.println(operation);
                    obj.selectAnEvent(obj);
                    System.out.println("-------------------------");

                } else {
                    serverSocket = new ServerSocket(server_Port);
                    //System.out.println("In receiver ###############################################################");

                    Socket socket = serverSocket.accept();
                    System.out.println("Connection established");
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    synchronized (vector_time) {
                        String sending_server = in.readUTF();
                        System.out.println("from " + sending_server);

                        String current_server = in.readUTF();
                        int amount = Integer.parseInt(in.readUTF());
                        String vector_incoming = in.readUTF();
                        obj.processTheIncomingArrayString(vector_incoming, current_server);
                        balance += amount;

                        System.out.println("Vector clock at " + current_server + " Process is:");
                        System.out.println(vector_time[0] + "  " + vector_time[1] + "  " + vector_time[2]);
                        serverSocket.close();
                    }

                }

            }
        } catch (Exception ex) {

            System.out.println("Exception Occurred");
        }

    }
    
    
    
    
    public String bfsTrees(String id) {
    	
    	
        try {

			/* Your initialization code goes here */
			
            while (waitForNextRound()) {  
            
			/* Your algorithm goes here */
            }
        } catch(SimulatorException e){
            System.out.println("ERROR: "+e.getMessage());
        }
        
        return null;
    }
    
    /* Print information about the parent and children of this processor in both BFS trees */
    private void printParentsChildren(String parent1, String parent2, Vector<String>children1, Vector<String>children2) {
    		String outMssg = "["+parent1+":";
    		for (int i = 0; i < children1.size()-1; ++i)
    			outMssg = outMssg + children1.elementAt(i)+" ";
    		if (children1.size() > 0)
    			outMssg = outMssg + children1.elementAt(children1.size()-1) + "] [" + parent2 + ":";
    		else outMssg = outMssg + "] ["+parent2+":";
    		for (int i = 0; i < children2.size()-1; ++i)
    			outMssg = outMssg + children2.elementAt(i)+" ";    
    		if (children2.size() > 0)                    
    			outMssg = outMssg + children2.elementAt(children2.size()-1)+ "]";    
    		else outMssg = outMssg + "]";
    		showMessage(outMssg);
    		printMessage(outMssg);    

    		Check.verify(getID(),parent1,parent2,children1,children2);
}

}