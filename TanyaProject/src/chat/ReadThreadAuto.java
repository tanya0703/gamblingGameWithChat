package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThreadAuto extends Thread{
	private BufferedReader reader;
	private String messageRead ;
    private Socket socket;
    private ChatAuto client;
 
    public ReadThreadAuto(Socket socket, ChatAuto client) {
        this.socket = socket;
        this.client = client;
        this.messageRead = null;
 
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    public String read() {
            try {
                this.messageRead = reader.readLine();
                System.out.println("\n" + this.messageRead);
                
                if (client.getUserName() != null) {
                    System.out.print("[" + client.getUserName() + "]: ");
                }
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
            }
            return this.messageRead;
        }
   // }
    
}
