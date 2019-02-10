package chat;

import java.io.*;
import java.net.*;

public class ReadThread extends Thread{
	private BufferedReader reader;
	private String response ;
    private Socket socket;
    private ChatClient client;
 
    public ReadThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
        this.response = null;
 
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public String giveResponse() {
    	return this.response;
    }
    
    public void run() {
        while (true) {
            try {
                this.response = reader.readLine();
                System.out.println("\n" + this.response);
 
                if (client.getUserName() != null) {
                    System.out.print("[" + client.getUserName() + "]: ");
                }
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
