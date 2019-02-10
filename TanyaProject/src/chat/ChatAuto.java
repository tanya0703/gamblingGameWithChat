package chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.syn.n.bad.annotation.TextToken;

public class ChatAuto {;
    private String userName;
 
    public ChatAuto() {
    }
 
    public void execute() {
        try {
            Socket socket = new Socket("localhost", 9099);
 
            System.out.println("Connected to the chat server");
            ReadThreadAuto rt = new ReadThreadAuto(socket, this);
            WriteThreadAuto wta = new WriteThreadAuto(socket, this);
            wta.sendName();
            while (true) {
            		String messageRead = rt.read();
                	String check = messageRead.substring(8,12);
                	if (messageRead == null) {
                    	messageRead = "Hello ai";
                        wta.answering(messageRead);
                	} else if ((check.equals("part")|| (check.equals("sell")) || (check.equals("posi")) )) {
                		;
                	} else {
                        wta.answering(messageRead);
                	}
            }
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
        ChatAuto client = new ChatAuto();
        client.execute();
    }
}
