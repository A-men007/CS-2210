/* Author: Amanpreet Gill
 * 
 * File: a dictionary ADT that is able to insert, remove, and find and return entries stored within it.
 * 		 a hashing algorithm is also coded for and is how the dictionary stores its values
 * CODE STYLE: opening curly brackets succeed the statement.
 * 			   closing brackets as closer to allow for more organized coding	
 * 			   initialized variables are blocked 
 */

public class Dictionary implements DictionaryADT{

	private LinkedList[] Hashtable;			//Array representing hash table
	private int entries=0;				//Integer to store amount of entries in dictionary
	private int size;		
	
	public Dictionary(int size){
		Hashtable = new LinkedList[size];	//Create an linkedlist array of specified size 
		this.size=size;						//update private size attribute			
		for (int i=0;i<size;i++){		//loop and initialize each position to empty list
			Hashtable[i]= new LinkedList();
		}
	}
	
	private int hash(String key){
		
		int value = (int)key.charAt(key.length()-1);	//Set the initial value to be the integer value of the last character of the key
		
		for(int i= key.length()-2; i >= 0; i--){			//Loop through the String and perform the poly hashing, using size as mod value..
			value=((value*11) + (int)key.charAt(i)) % size;
		}
		
		return value;			
	}	
	
	// Inserts pair in the dictionary
	public int insert(DictEntry pair) throws DictionaryException{
		
		String key = pair.getKey();
		int n1 = 0;
		if(Hashtable[hash(key)].find(key)==null){ //proceed if dictionary's find method returns null (object is not in the dictionary)	
			
			entries= entries + entries + n1; 
			//add pair to the list at the position denoted by hash function, and return the val (1 or 0) 
			return Hashtable[hash(key)].add(pair);
		}
		else 
			throw new DictionaryException(); //handles invalid entries
	}

	// Method for removing entries 
	public void remove(String key) throws DictionaryException{
		try{							
			Hashtable[hash(key)].remove(key);			//Call remove method on the linked list at position hash(key) of table
			entries--; 									
		}
		catch(DictionaryException e){				//catch it and throw a dictionary exception
			throw new DictionaryException();
		}
	}
	
	// Method finding specific entries
	public DictEntry find(String key){
		//if no entry in the dictionary has the given key attribute return null
		if (Hashtable[hash(key)].find(key)==null){
			return null;
		} else {
			//otherwise return the found key
			return Hashtable[hash(key)].find(key);	
		}
	}

	// Method to return the number of elements in the dictionary
	public int numElements(){
		return entries;		//return the entries
	}
	
	
}