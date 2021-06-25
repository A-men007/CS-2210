/**
 * @author amanpreet gill
 * SN:250964445
 */
public class Edge {

	/**
	 * 
	 */
	//Attributes
		private Node start, end;		
		private int type;//type of edge	
		private String label;//edge label
		
		//Constructor
		public Edge(Node u, Node v, int type){
			start = u;			
			end = v;
			this.type = type;
		}
		
		public Edge(Node u, Node v, int type, String label) {
			start = u;			
			end = v;
			this.type = type;
			this.label = label;
		}
		
		//Get method for start
		Node firstEndpoint(){
			return start;
		}
		
		//Get method for end
		Node secondEndpoint(){
			return end;
		}
		
		//Get method for type
		int getType(){
			return this.type;
		}
		
		void setType(int newType) {
			this.type = newType;
		}
		
		//Set method for label
		void setLabel(String label){
			this.label = label;
		}
		
		//Get method for label
		String getLabel(){
			return label;
		}

}
