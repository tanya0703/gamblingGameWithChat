package chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WriteThread extends Thread {
	private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
 
    public WriteThread(Socket socket, ChatClient client) {
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
 
    public void run() {
    	
	    Scanner reader = new Scanner(System.in);
        System.out.print("\nEnter your name: ");
        String userName = reader.nextLine();
        client.setUserName(userName);
        writer.println(userName);
 
        String text;
 
        do {
        	System.out.print("[" + userName + "]: ");
        	text = reader.nextLine();
            writer.println(text);
 
        } while (!text.equals("bye"));
 
        try {
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
    
    void sendMessage(String message) {
        writer.println(message);
    }
}
