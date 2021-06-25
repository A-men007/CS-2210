/* Author: Amanpreet Gill
 * 
 * File: a dictionary ADT that is able to insert, remove, and find and return entries stored within it.
 * 		 a hashing algorithm is also coded for and is how the dictionary stores its values
 * CODE STYLE: opening curly brackets succeed the statement.
 * 			   closing brackets as closer to allow for more organized coding	
 * 			   initialized variables are blocked 
 */

import java.util.LinkedList;

public class Dictionary implements DictionaryADT // Constructor
{

	private int entries = 0;
	private LinkedList<Record>[] hashTable; 
	int size;

	public Dictionary(int size)
	/*
	 * dictionary that uses a hash-table with separate chaining. 
	 * param is dictionary size.
	 */
	{
		
		hashTable = (LinkedList<Record>[]) new LinkedList[size];
		this.size = size;
		int tableLength = hashTable.length;
		
		for(int i = 0; i < tableLength; i++)
		{
		
			hashTable[i] = new LinkedList<Record>();
			
		}
		
		entries = 0;
	
	}
	
	
	public int insert(Record pair) throws DictionaryException 
	/*
	 * inserts given Record pair into the dictionary. 
	 * returns 1 if the pair insertion produces a collision and 0 otherwise.
	 * throws a dictionary exception if pair is already in the dictionary.
	 * param is the config and score paired together as one 
	 */
	{
		
		int key = hashFunction(pair.getConfig());
		int hashSize = hashTable[key].size(); 
		int a = 0;
				
			while (a < hashSize)
			{
			
				if ((hashTable[key].get(a).getConfig().compareTo(pair.getConfig()) == 0) || hashTable[key] == null) 
				{
					a++;
					throw new DictionaryException(); 
				}
				
				else
				{
				
					a++;
					entries++;
					hashTable[key].addLast(pair);
					return 1;
				
				}
				
			}
		 
		hashTable[key].add(pair); 
		return 0; 
	
	}
	
	public void remove(String config) throws DictionaryException
	/*
	 * removes a config entry from the dictionary. 
	 * throws a dictionary exception if config is not found in the dictionary.
	 * param is the String form of the gameboard configuration.
	 */
	{
		
		int removeNode = hashFunction(config);
		
		try
		{
			
			hashTable[removeNode].remove();
			entries--;
			
		}
		
		catch (Exception emptyList)
		{
			
			throw new DictionaryException(); 
			
		}
		
	}  
		
	
	
	public int get(String config)
	/*
	 * returns the score of a given configuration.
	 * returns =1 if the configuration is not present in the dictionary.
	 * param is the string form of the gameboard configuration.
	 */
	{
		
		int getNode = hashFunction(config);
		int nodeSize = hashTable[getNode].size();
			
		for (int i = 0; i < nodeSize; i++)
		{
			
			if (hashTable[getNode] == null)
			{
				
				continue;
				
			}
			
			if (hashTable[getNode].get(i).getConfig().compareTo(config) == 0)
			{
				
				return hashTable[getNode].get(i).getScore(); 
			
			}
			
		}
				
		return -1;
			
		
	}
	
	public int numElements() //returns the number of Record objects stored in the dictionary
	{
	
		return entries; 
	
	}
		
	private int hashFunction(String config) //
	/*
	 * returns the int value to be implemented into the hash table.
	 * param is the string form of the gameboard configuration.
	 * hash function that converts a string into an int 
	 */
	{
		
		double hash = 97.51; 
		double hashVal = 0;
		int length = config.length();
		int hashLength = hashTable.length;
		int hashInt = 0;
		
		for (int x = 0; x < length; x++) //algorithm hashFunction; multiplies hashVal (0 first) 
		{
			
			 hashVal = ((hashVal * hash) + (config.charAt(x)*length)) % hashLength;
			 hashInt = (int) hashVal; //converts long type to int type
			 
		}
		
		return Math.round(hashInt); // rounded for whole numbers
		
	}
	
		
		
}
