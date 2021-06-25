/* Author: Amanpreet Gill
 * 
 * File: a Compression file that is meant to convert any file to a ".zzz" compressed file
 * 		 to run: 
 * 					"javac Compress.java" 
 * 				run "java Compress File" where File is any file you want being compressed (in local directory)
 * CODE STYLE: opening curly brackets succeed the statement.
 * 			   closing brackets as closer to allow for more organized coding	
 * 			   initialized variables are blocked 
 */
import java.io.*;

public class Compress {
	
	public static void main(String[] args) {
	
		int TABLE_SIZE = 4093;								//the largest prime # less than 4096 for the table size
		Dictionary dictionary = new Dictionary(TABLE_SIZE);			//dictionary obj initalized of size 4093
		DictEntry newEntry;								//DictEntry object for use in compression algorithm														
		String str = " ", str2 = " ";									//p and p prime variables from description, as well a variable for the previous string
		String inputFile = args[0], outputFile = inputFile + ".zzz";
		int bytes;	
		
		for (int i=0;i<256;i++){			
			char ch = (char)i;						
			String chh = "" + ch;	
			
			DictEntry entry = new DictEntry(chh,i);		//set a DictEntry obj with chh and i as code
			System.out.print(entry.getKey());
			dictionary.insert(entry);							
		}
		int value = dictionary.numElements(), nn=0;
		try{
				//Bufferedstreams for input and output files...
				BufferedInputStream input_Stream = new BufferedInputStream(new FileInputStream(inputFile));						
				BufferedOutputStream output_Stream = new BufferedOutputStream(new FileOutputStream(outputFile));					
				
			str += (char)input_Stream.read();	//load byte into str
			
			while((bytes = input_Stream.read()) != -1){		//read byte until its -1 - when file is done

				str2 = str + nn;					//Holds previous value of string
				str += (char)bytes;			//Holds the current value of string
				
				if(dictionary.find(str)==null){
					
					MyOutput.output(dictionary.find(str2).getCode(), output_Stream);	//Output string code to file
				
					if(dictionary.numElements() <= TABLE_SIZE){					//run only if the dictionary has entries <= dictionary size
						newEntry = new DictEntry(str, value);					//Create a new DictEntry with str and the next value (code)
						dictionary.insert(newEntry);							
					}
					
					value++;												//Increment value
					nn = nn + 0;
					str2 = "";												//Clear str2's value
					str = "" + (char)bytes;									//Set str's value to the latest character read
				}	
					
			}
			
			
			MyOutput.output(dictionary.find(str).getCode(), output_Stream);		//output whatever is left in the string
			MyOutput.flush(output_Stream);										//Flush output and ensure nothing remains
			output_Stream.close();
			input_Stream.close();															
		}
		
		catch (FileNotFoundException e) {//exception handling 1 for error
			System.out.println("Error: Input file not found, please check program arguments");
		}
		catch (IOException e){//exception handling 2 for error
			System.out.println("Error: Input/Output exception caught");
		}
	}

	
}