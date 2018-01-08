import java.io.*;
import java.net.*;
import java.util.*;

public class XMLParser {

   public static void main(String args[]) {
	   
	   try {
		   
		   // Pulls the information from the rss feed
	    	URL url = new URL("https://www.mangaupdates.com/rss.php");
	    	InputStream stream = url.openStream();
	    	BufferedReader reader = new 
	    			BufferedReader(new InputStreamReader(stream));
	    	
	    	BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
	    	writer.write("start\n");
	    	
	    	// Only grabs the important content needed
	    	String line;
	    	ArrayList<ArrayList<String>> listOfContent = new ArrayList<ArrayList<String>>();
	    	ArrayList<String> content = new ArrayList<String>();
	    	
	    	boolean hasStuff = false;
	    	while ((line = reader.readLine()) != null) {
	    		line = line.trim().replace("&amp;", "&").replace("&gt;", ">").replace("&lt;", "<");
	    		
	    		if(hasStuff && !(line.contains("</item>") && line.contains("<item>"))) {
	    			content.add(line);
	    			writer.append(line);
	    		}
	    		
	    		if(line.contains("</item>")) {
	    			hasStuff = false;
	    			ArrayList<String> temp = new ArrayList<String>(content);
	    			listOfContent.add(temp);
	    			content.clear();
	    		}
	    		
	    		if(line.contains("<item>")) {
	    			hasStuff = true;
	    		}
	    	}
	    	
	    	System.out.println(listOfContent);
	    	writer.close();
	    	
	   } catch (IOException error) {
		   System.out.println("uh oh");
	   }
   }
   


}