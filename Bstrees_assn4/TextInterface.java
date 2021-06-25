/**
 * @author Amanpreet
 * CS2210: Asn 4 
 * this class implements the user interface and contains the main dictionary.
 * */

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;

public class TextInterface {

	//main interface class 
    public static void main(String[] args) throws DictionaryException, MultimediaException {
        //initialize
    	StringReader keyboard = new StringReader();
        PictureViewer pictureViewer = new PictureViewer();
        RunCommand runCommand = new RunCommand();
        ShowHTML Html = new ShowHTML();
        OrderedDictionary dictionary = new OrderedDictionary();
        SoundPlayer soundPlayer = new SoundPlayer();
        
        //set filename to argument
        String filename = args[0];		
        //check arg size to just one file
        if (args.length != 1) {
        	System.out.println("Usage: Java UI <filename>");
        	System.exit(0);
        }
        
        //main procedure if checks are good so far
        try {
            //read in file
        	BufferedReader in = new BufferedReader(new FileReader(filename));
        	String newline;
            String keyword = in.readLine();
            
            //stores dictionary entries
            try {
            	//make sure that end of file isn't hit
                while (keyword != null) {
                    try {
                    	//read in line
                    	newline = in.readLine();
                        //for images .gifs/jpgs
                    	if (newline.contains(".gif") || (newline.contains(".jpg"))) {
                            dictionary.put(new DataItem(new Key(keyword, "picture"), newline));
                            //System.out.println(dictionary.get(new Key(keyword, "picture")).getContent());
                        }
                    	//for executable .exe
                       	else if (newline.contains(".exe")) {
                            dictionary.put(new DataItem(new Key(keyword, "program"), newline));
                            //System.out.println(dictionary.get(new Key(keyword, "program")).getContent());
                        }
                    	 //for sound .wav/mid
                    	else if (newline.contains(".wav") || newline.contains(".mid")) {
                            dictionary.put(new DataItem(new Key(keyword, "sound"), newline));
                            //System.out.println(dictionary.get(new Key(keyword, "sound")).getContent());
                        }
                    	//for pages .html
                    	else if (newline.contains(".html")) {
                            dictionary.put(new DataItem(new Key(keyword, "url"), newline));
                            //System.out.println(dictionary.get(new Key(keyword, "url")).getContent());
                        }
                        //for text .txt
                    	else{
                            dictionary.put(new DataItem(new Key(keyword, "definition"), newline));
                            //System.out.println(dictionary.get(new Key(keyword, "definition")).getContent());
                        }
                        //iterate next line
                    	keyword = in.readLine();
                    } //throw exception if line don't match previous
                    catch (Exception e) {
                        System.out.println("Value input error due to failure");
                    }
                }
            } 
            catch (Exception e) {
                System.out.println("Putting values error due to failure");
            }
             finally {
    			in.close();
            }
            in.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File could not be opened: " + filename);
        } 
        catch (IOException e) {
        	System.out.println("Cannot open file: " + filename);
        }
    
        //user prompt 
        System.out.println("Welcome to my Multimedia Dictionary! \n put: insert into dictionary \n " 
        		+ "get: retrieve from dictionary \n delete: remove from dictionary "
        		+ "\n list: find all text with prefix \n first: retrieve smallest key in dictionary \n last: "
        		+ "retrieve the largest key in dictionary \n finish: end program \n");
        do {
            String command = keyboard.read("Enter command: ");
            String commandParsed[] = command.toLowerCase().split("\\s");
            String stringCombined = "";

            for (int i = 3; i < commandParsed.length; i++) {
                stringCombined += commandParsed[i] + " ";
            }

            switch (commandParsed[0]) { 
            	//used to insert into dictionary
                case "put":
                    Key key = new Key(commandParsed[1], commandParsed[2]);
                    DataItem DataItem = new DataItem(key, stringCombined);
                    try {
                        dictionary.put(DataItem);
                    } 
                    catch (DictionaryException e) {
                        System.out.println(("A DataItem with the given key (" + commandParsed[1] + " ," + commandParsed[2] + ")" + " is already in the ordered dictionary."));
                    }
                    System.out.println();
                    break;
                //used to find smallest key in dictionary
                case "first":
                    System.out.println(dictionary.smallest().getKey().getName() + ", " + dictionary.smallest().getContent());
                    break;
                //used to find largest in dictionary
                case "last":
                	System.out.println(dictionary.largest().getKey().getName() + ", " + dictionary.largest().getContent());
                    break;
                //used to delete from dictionary
                case "delete":
                    Key keyDelete = new Key(commandParsed[1], commandParsed[2]); 
                    try {
                        dictionary.remove(keyDelete);
                    } 
                    catch (DictionaryException e){
                        System.out.println("No DataItem in the ordered dictionary has key (" + commandParsed[1] +", " + commandParsed[2] + ")");
                    }

                    System.out.println();
                    dictionary.preOrder();
                    break;
                //used to search dictionary by prefix's
                case "list":
                	String prefix = commandParsed[1];
					DataItem w = dictionary.successor(new Key(prefix, "definition"));
					String r = w.getKey().getName();

					while(r.startsWith(prefix) || (!r.isEmpty())){
						System.out.println(r);
						w = dictionary.successor(new Key(r, "definition"));
						r = w.getKey().getName();
					}
                    break;
                //used to get from dictionary
                case "get":
                	// three new key objects
                	Key img = new Key(commandParsed[1],"picture");
                    Key text = new Key(commandParsed[1],"definition");
                    Key audio = new Key(commandParsed[1],"sound");
                    Key prgm = new Key(commandParsed[1],"program");
                    Key html = new Key(commandParsed[1],"url");
                    // three new record objects
                    DataItem textFound = dictionary.get(text);
                    DataItem imgFound = dictionary.get(img);
                    DataItem audioFound = dictionary.get(audio);
                    DataItem prgmFound = dictionary.get(prgm);
                    DataItem htmlFound = dictionary.get(html);
                    
                    boolean exists = false;
                    
                    	if(textFound != null){
                    		System.out.println(textFound.getContent());
                            exists = true;
                        }
                    	if(audioFound != null) {
                            try {
                                soundPlayer.play(commandParsed[1] + ".wav");
                            } 
                            catch (MultimediaException e){
                                try {
                                    soundPlayer.play(commandParsed[1] + ".mid");
                                } 
                                catch (MultimediaException ignore) {
                                }
                            }
                            exists = true;
                        }
                        if(imgFound != null) {
                            try {
                                pictureViewer.show(commandParsed[1] + ".gif");
                            } 
                            catch (MultimediaException e) {
                                try {
                                    pictureViewer.show(commandParsed[1] + ".jpg");
                                } 
                                catch (MultimediaException ignore) {
                                }
                            }
                            exists = true;
                        }
                        if (prgmFound != null) {
                            //try {
                                runCommand.run(commandParsed[1] + ".exe");
                            //} 
                        }
                        if (htmlFound != null) {
                            //try {
                                Html.show(commandParsed[1] + ".html");
                            //} 
                        }
                        if (!exists) {
                            System.out.println("The word " + commandParsed[1] + " is not in the ordered dictionary");
                            dictionary.put(new DataItem(new Key(commandParsed[1], "text"), "text"));
                            System.out.println();
                            dictionary.preOrder();
                            System.out.println();
                            DataItem pre = dictionary.predecessor(new Key(commandParsed[1], "definition"));
                            DataItem post = dictionary.successor(new Key(commandParsed[1], "definition"));
                            System.out.println("Preceding word: " + pre.getKey().getName());
                            System.out.println("Following word: " + post.getKey().getName());
                            dictionary.remove(new Key(commandParsed[1], "definition"));
                        }
                    break;
                //used to exit program
                case "finish":
                    System.out.println("Thanks! Have a good day.");
                	return;
                //used to find wrong commands
                default:
                    System.out.println("That is an invalid command, please try again");
                    System.out.println();
                    break;
            }
        }
        while (true);
    }
}