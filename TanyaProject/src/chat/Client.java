package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


import java.io.*;
import java.net.*;
import java.util.Scanner;
import org.syn.n.bad.*;
import org.syn.n.bad.annotation.*;
import org.syn.n.bad.pattern.*;
import org.syn.n.bad.annotation.TextAnnotation;
import org.syn.n.bad.pattern.token.TokenMatcher;
import org.syn.n.bad.pattern.token.*;
import org.syn.n.bad.annotation.TextAnnotation;
import org.syn.n.bad.annotation.TextToken;
import org.syn.n.bad.annotation.TextAnnotation;
import org.syn.n.bad.annotation.TextToken;
import java.util.Locale;


public class Client {
	private Matcher matcher;

	  public static void main(String args[]) throws Exception{
	    Client kliEnt = new Client();
	    kliEnt.run();
	  }

	  public void run() throws Exception{
	    matcher = new Matcher();
	    //matcher.addMatcher(new PatternMatcher("1", "[animal] goes wild"));
	    matcher.addMatcher(new PatternMatcher("2", "Do you want to participate", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("3", "How are you?", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("4", "How much money can you pay for that?", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("5", "How many cards can you propose for that?", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("6", "I'm proposing $name1? cards.", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("7", "I'm proposing $name1? card.", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("8", "Bye", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("9", "tanya $name2* sandwiches", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("10", "What are you $name3*", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("11", "brute goes wild"));
	    //matcher.addMatcher(new PatternMatcher("12", "feeling [good|RB*#attribute]"));

	    //matcher.addMatcher(new PatternMatcher("12", "I want [some|RB*] water"));


	    //matcher.addMatcher(new PatternMatcher("8", "feeling [good|RB*#attribute]"));
	    //matcher.addMatcher(new PatternMatcher("8", "feeling [RB]"));



	    //matcher.addMatcher(new PatternMatcher("7", "feeling [good|better]"));

	    /*TextAnnotation tokens = new TextAnnotation(3);
	        tokens.addTextToken(new TextToken("Hey"));
	        tokens.addTextToken(new TextToken("how"));
	        tokens.addTextToken(new TextToken("are"));
	        tokens.addTextToken(new TextToken("you?"));*/

	    //MatchResult result = matcher.match(tokens, 0, tokens.size());
	    //System.out.println(result.getTemplateID());
	    //TextAnnotation tokens = new TextAnnotation(3);
	      //tokens.addTextToken(new TextToken("how"));
	      //tokens.addTextToken(new TextToken("are"));
	      //tokens.addTextToken(new TextToken("you"));
	    //MatchResult result = matcher.match(tokens, 0, tokens.size());
	    //System.out.println(result.getTemplateID());
	    //pt = new PatternMatcher("1", "hi");
	    //System.out.println(pt.getId());
	    //matcher.addMatcher(pt);

	    //TextAnnotation tokens = new TextAnnotation(4);

	    Socket sock = new Socket("localhost",9099);
	   // Boolean stop = false;
	    String autoMess = "null";
	    while (true){
	      PrintStream ps = new PrintStream(sock.getOutputStream());// what's going out from the client  to the server
	      Scanner reader = new Scanner(System.in);
	      if (autoMess == "null") {
	        System.out.print("Client : ");
	        String messClient = reader.nextLine();
	        ps.println(messClient); //sending a message from the client to the server - the administrator of the server will see that
	      } else {
	        System.out.print("Client : " + autoMess);
	        ps.println(autoMess);
	      }
	      //  String[] words = messClient.split("\\W+");

	      /*  if (messClient != null){
	          sendmess(ps, messClient);
	          //stop = true;
	        } else {*/
	        //sending a message from the client to the server - the administrator of the server will see that


	        InputStreamReader ir = new InputStreamReader(sock.getInputStream()); // listening for something coming from the server
	        BufferedReader br = new BufferedReader(ir);

	        String message = br.readLine(); // receiving the message from the server

	        String[] words = message.split(" ");
	        TextAnnotation tokens = new TextAnnotation(words.length);
	        MatchResult result;
	        Integer id = 0;
	        Integer k = 0;
	        while ((id == 0) && (k <= words.length)){
	        	for (int i=0; i < words.length;i++){
	        		tokens.addTextToken(new TextToken(words[i]));
	        		System.out.println(tokens.getTokens());
	        		result = matcher.match(tokens, 0, tokens.size());
	        		k++;
	        		//id = Integer.parseInt(result.getTemplateID());
	        		System.out.println(result.getTemplateID());

	        	}
	        }
	        Annotation annotation = new Annotation();
	        annotation.addToken(new AnnotationToken(1, TextAnnotationConstants
	                                                           .transformAnnotationLabel(TextAnnotationConstants
	                                                                                             .getLevel(TextAnnotationConstants.POS),
	                                                                                     "RB")));
	        tokens.addAnnotation(TextAnnotationConstants.getLevel(TextAnnotationConstants.POS), annotation);
	        //MatchResult result = matcher.match(tokens, 0, tokens.size());

	        //System.out.println(annotation.getTokens());
	        //System.out.println("ID: "+ result.getTemplateID());
	        //System.out.println(result.getMatchedVars());

	      //  System.out.println(result.getMatchedVars().get("#attribute"));
	        //System.out.println(result.getStyleLabels().get("mood"));

	        System.out.println("Server : " + message); // printing the message
	        //Locale language="lang=en";
	        //String pattern = "mood=good";
	        //TokenMatcher currentMatcher = TokenMatcherFactory.factoryPatternMatcher(language, pattern);
	        //System.out.println(currentMatcher.getMatcherList());
	        //String id = result.getTemplateID();
	        //if (!Strings.isNullOrEmpty(id)){
	         // switch (id){
	          //  case "1": autoMess = "Yes, of course!";
	           // case "2": autoMess = "Fine, thanks! ";
	            //case " ": autoMess = "bfbfbf";
	       // }
	      //} else {
	       // autoMess = "null";
	      //}
	     //   System.out.print("Automess: "+ id);
	    }
	}
	    public void sendmess(PrintStream ps, String mess){
	      ps.println(mess);
	    };
}
