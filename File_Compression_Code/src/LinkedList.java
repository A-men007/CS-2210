/* Author: Amanpreet Gill
 *
 * File: a basic linkedlist class template that is able to insert, remove, and find and return entries stored within it.
 */

public class LinkedList {	
	private int sz;
	private LinkedNode head;				
	
	public LinkedList(LinkedNode h){		//If constructed with an entry, make it the head and increment size
		head=h;
		sz=1;
	}
	public LinkedList(){				
		head=null;
		sz=0;
	}
	
	//	Method to add an entry in list
	public int add(DictEntry newEntry){
		int result = 0;			
		LinkedNode newHead = new LinkedNode(newEntry);	
		
		if(sz!=0) {		
			result = 1;
		}
		newHead.setNext(head); 	
		head=newHead;			
		sz++;					
		return result;			
	}
	
	//	Method to delete an entry in list
	public void remove(String key) throws DictionaryException{
		if(sz==0)								
			throw new DictionaryException();
						
		LinkedNode prev = null;		
		LinkedNode curr = head;
		boolean flaggedkey = false;
		
		if(curr.getEntry().getKey().equals(key)){	
			head=curr.getNext();
			sz--;
			return;
		}
		else{
		
			while(curr != null && !flaggedkey){		//Loop through list until either key is found or list finishes
				if(curr.getEntry().getKey().equals(key)){
					flaggedkey = true;				//If found, change to true
				}
				else {		
					prev = curr;
					curr = curr.getNext();
				}
			}
		
			if (!flaggedkey)				//If target was not found, throw an exception
				throw new DictionaryException();
		
			if(sz==1){						//If there was only one entry in the list and the target was found, set head to point to null
				head=null;
				sz --;
				return;
			}
		
			prev.setNext(curr.getNext());	
			sz--;									
			return;
		}
	}

	
	//	Method to find an entry in the list
	public DictEntry find(String key) {
		if(sz==0)							
			return null;
		
		LinkedNode curr = head;			
		while(curr != null){				

			if(curr.getEntry().getKey().equals(key))
				return curr.getEntry();				//If the curr node's entry's key is the same as the target key, return that entry

			curr=curr.getNext();		
		}
		return null;							//If the loop exits and target was not found -> return null
	}
	
	
	//	Method to return the size of the list
	public int size(){
		return sz;
	}

}