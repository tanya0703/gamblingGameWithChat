package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur {
	public static void main(String args[]) throws Exception{
	    Serveur server = new Serveur();
	    server.run();
	  }

	  public void run() throws Exception{
	    ServerSocket srvSock = new ServerSocket(9099); // port 9099 localhost
	    Socket sock = srvSock.accept();
	    InputStreamReader ir = new InputStreamReader(sock.getInputStream());
	    BufferedReader br = new BufferedReader(ir);
	    Scanner reader = new Scanner(System.in);
	    Boolean stop = false;

	    while (stop != true){
	      String message = br.readLine();
	      System.out.println("Client : " + message);//message from the client
	      if (message != null){ //if we received the message
	        if (message.equals("Bye")){
	          stop = true;
	        } else {
	          PrintStream ps = new PrintStream(sock.getOutputStream()); // we send to the client thanks to a printStream
	          System.out.print("Server : ");
	          String messServer = reader.nextLine();
	          ps.println(messServer); // this is the message sent
	        }
	      }
	    }
	  }
}
