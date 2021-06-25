/* Author: Amanpreet Gill
 * 
 * File: A class represents a record to be stored in a dictionary that you
 * 		 must implement using a hashtable
 * CODE STYLE: opening curly brackets succeed the statement.
 * 			   closing brackets as closer to allow for more organized coding
 * 			   added spacing between each function to allow for easier traversal
 * 			   initialized variables are blocked 
 */
public class DictEntry {
	
	private String key; //stores key attribute of dictionary obj
	private int code;   //stores code for obj 
	
	public DictEntry(String theKey, int theCode) {
		this.key = theKey;
		this.code = theCode;
	}
	
	//getters
	public String getKey() {
		return this.key;
	}
	
	public int getCode() {
		return this.code;
	}
	
	//function checks if entries are unique or not
	public boolean isEqual(DictEntry secondObject) {
		if ((this.key == secondObject.key) && (this.code == secondObject.code)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}