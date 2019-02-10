package chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private int port=9099;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
 
    public ChatServer() {
    }
 
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
  
            while (true) {
                Socket socket = serverSocket.accept(); 
                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();
 
            }
 
        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
 
        ChatServer server = new ChatServer();
        server.execute();
    }
 

    void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }
 
    void addUserName(String userName) {
        userNames.add(userName);
    }
 
    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
 
    Set<String> getUserNames() {
        return this.userNames;
    }
 
    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}