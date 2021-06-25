/**
 * @author AmanpreetG
 */
import java.util.ArrayList;
import java.util.LinkedList;
public class Dictionary implements DictionaryADT {
		
		// Initialize variables for the hashtable, the number of elements and the size of the hashtable
		private ArrayList<LinkedList<Data>> hashtable = new ArrayList<>();
		private int numElemen;
		private int size;
		
		// Define constructor
		public Dictionary(int size){
			// define the size of the table
			this.size = size;
		
			// populate table
			hashtable = new ArrayList<LinkedList<Data>>(size);
			int i = 0;
			while(i < this.size){
				hashtable.add(null);
				i++;
			}
			// initialize the number of elements
			numElemen= 0;
		}

		/// Calculate the hash value for each key
		private int hashFunc(String config){
			int value = 0;
			for(int index = 0; index < config.length(); index++){
				value += (value * 59 + (int)config.charAt(index)) % size;
			}
			//return the hashed value
			return value % size;
		}
		
		// Insert a Data
		public int put(Data record) throws DuplicatedKeyException{	
			int hashValue = hashFunc(record.getKey()); 
			// get corresponding key value
			LinkedList<Data> list = hashtable.get(hashValue);
			// check to see if the list isn't empty
			if (list != null) {		
				if (get(record.getKey()) != null) {
				    throw new DuplicatedKeyException("key already exists");
				} 
				// otherwise there is a collision
		    	list.add(record);
		    	
		    	// add another element to the count
		    	numElemen++;
		    	return 1;	
			}
			else {
				// create a new linked list otherwise
				LinkedList<Data> newList = new LinkedList<Data>();
				
				// add the Data to the linked list
				newList.add(record);
				// add the list to the dictionary
				hashtable.set(hashValue, newList);
				
				// add another element to the count
		    	numElemen++;
				return 0; 
			}
			
		}

		// Remove a Data from dictionary
		//goes through list and checks to fing key match
		//recursively removes key and reduces the total number of elements
		public void remove(String key) throws InexistentKeyException{
			int hashValue = hashFunc(key);
			if (hashtable.get(hashValue) != null) {
				LinkedList<Data> list = hashtable.get(hashValue);
				for (int i = 0; i < list.size(); i++) {		
				Data element = list.get(i);
				if(element.getKey().equals(key)) {
					list.remove(i);
					// reduce the number of items in the dictionary
					numElemen--;
					
				}
			}		
		} else 
				// throw an exception if there's an empty list
				throw new InexistentKeyException("key doesn't exist");
		}

		// Get the Data for the given configuration
		public Data get(String key){
			// compute the hash value of the key
			int hashValue = hashFunc(key);	
			if (hashtable.get(hashValue) != null) {
				// retrieve the linked list for the hash value
				LinkedList<Data> list = hashtable.get(hashValue);	
				for (int i = 0; i < list.size(); i++) {	
					Data record = list.get(i);		
					//configuration exists already...
					if(record.getKey().equals(key)) {
						// return the score of that configuration
						return record;// NOT SURE ABOUT THIS
					}
				}
			}
			// if the Data or linked list is empty
			return null;
		}
		
		// Return the number of Data in the dictionary
		public int numDataItems(){
			return numElemen;
		}
}