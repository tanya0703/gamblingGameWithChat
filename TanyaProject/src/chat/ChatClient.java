package chat;

import java.net.*;
import java.io.*;

public class ChatClient {
	private String hostname;
    private int port;
    private String userName;
 
    public ChatClient() {
        this.hostname = "localhost";
        this.port = 9099;
    }
 
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port); 
            ReadThread rt = new ReadThread(socket, this);
            rt.start();
            
            new WriteThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
 
    }
 
    void setUserName(String userName) {
        this.userName = userName;
    }
 
    String getUserName() {
        return this.userName;
    }
 
 
    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.execute();
    }
}
