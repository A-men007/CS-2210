import java.util.*;
import java.util.Collections;
import java.util.ArrayList;
public class Degree {

	  public int maxDegree(Node r) {
		  	Node[] children = r.getChildren();
		  	ArrayList<Integer> degree = new ArrayList<Integer>(children.length);
		  	
		    for(int i=0;i<children.length;i++) {
		   
		    if(children[i].isLeaf()){
		        return 0;
		    }
		    else{
		    	int numchil = children[i].numChildren();
		        degree.add(numchil);
		    }

		    Collections.sort(degree);
		    
		}
		    //get the last value which will be the highest in the collection
		    return degree.get(degree.size()-1);

	  }
	  
}