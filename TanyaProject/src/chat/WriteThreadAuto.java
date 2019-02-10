package chat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteThreadAuto extends Thread {
	private PrintWriter writer;
    private Socket socket;
    private ChatAuto client;
 
    public WriteThreadAuto(Socket socket, ChatAuto client) {
        this.socket = socket;
        this.client = client;
        
 
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public PrintWriter getWriter() {
    	return this.writer;
    }
    
    public void sendName() {
    	String userName = "AI";
        client.setUserName(userName);
        writer.println(userName);
    }
    
 
    public void answering(String respondTo) {
 
        String text;
        	String userName = client.getUserName();
        	String actualMessage = respondTo.substring(respondTo.indexOf(" ")+1); 
        	Answer resp = new Answer();
        	resp.setQuestion(actualMessage);
        	System.out.print("[" + userName + "]: ");
        	text = resp.theAnswer();
        	System.out.println(text);
            writer.println(text);
}
    void sendMessage(String message) {
        writer.println(message);
    }
}
