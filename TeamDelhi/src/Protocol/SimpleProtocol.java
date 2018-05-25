package Protocol;

/**
 * Establishes a protocol to allow the Server and Client to communicate
 * 
 * @author Team Delhi
 */
public class SimpleProtocol{

	/**
	 * Method of nullifying potentially awkward characters
	 * @param str - String to be sent
	 * @return - New string with nullified values
	 */
    private static String nullify(String str) {
        str = str.replace("\\", "\\\\");
        str = str.replace("_", "\\_");
        str = str.replace("\n", "\\n");
        
        return str;
    }

    /**
     * Method for restoring awkward characters after decoding message
     * @param str - String that has been recieved
     * @return - New string with nullified values restored
     */
    private static String restore(String str) {
        str = str.replace("\\\\", "\\");
        str = str.replace("\\_", "_");
        str = str.replace("\\n", "\n");
        
        return str;
    }

    
    /**
     * Creates a message to be sent by the client or server
     * @param args - String arguments to be sent
     * @return - The String that has been sent
     */
    public String createMessage(String... args) {
    	
    	//Establish empty results string
        String result = "";
        
        //For String in arguments
        for (String str : args) {
        	
        	//If result string is not empty, add hash-values to end of last element
            if (result != ""){
            	result = result + "_###_";
            }
            
            //Nullify string and add to end of the result String
            result = result + nullify(str);
        }
        
 //       System.out.println("sent: " + result);
        
        //Return the result String
        return result;
    }

    
    /**
     * Decodes a message to be read by the client or server
     * 
     * @param str - Encoded String message sent
     * @return - Decoded String message
     */
    public String[] decodeMessage(String str) {
    	
    	//Print of recieved String
 //       System.out.println("received: " + str);
        
        //If String is null or empty, return empty string array
        if (str == null || str.equals("")) {
        	
            return new String[0];
            
        }
        
        //Split strings between hash-values and place into String array
        String[] messages = str.split("_###_");
         
        //For every message in string array 'messages'
        for (int i = 0; i < messages.length; i++) {
        	
        	//Restore nullified values
            messages[i] = restore(messages[i]);
            
        }
        
        //If String has only one entry and entry is empty, return empty string array
        if (messages.length == 1 && messages[0].equals("")) {
        	
            return new String[0];
            
        }
        
        //Otherwise, return the decoded message String array
        return messages;
    }

}
