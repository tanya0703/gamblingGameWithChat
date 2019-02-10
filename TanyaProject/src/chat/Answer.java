package chat;

import org.syn.n.bad.annotation.TextAnnotation;
import org.syn.n.bad.annotation.TextToken;
import org.syn.n.bad.pattern.MatchResult;
import org.syn.n.bad.pattern.Matcher;
import org.syn.n.bad.pattern.PatternMatcher;

public class Answer {
	private String Question;
	
	public Answer() {
	}
	
	public void setQuestion(String Question) {
		this.Question=Question;
	}
	
	public String[] toWords(String expression){
        String[] words = expression.split(" ");
        return words;
	}
	
	//public String[] findSynonyms(String Word) {
		
	//}
	
	public String resultInVariable(MatchResult mr, String nameVar) {
		return mr.getMatchedVars().get(nameVar);
	}
	public Integer idResult(MatchResult mr) {
		return Integer.parseInt(mr.getTemplateID());
	}
	
	public String idCardtoName(Integer id) {
		String nameCard;
		switch (id) {
        case 1:  nameCard = "Bijoux";
                 break;
        case 2:  nameCard = "Cigares";
                 break;
        case 3:  nameCard = "Cafe";
                 break;
        case 4:  nameCard = "Diamant";
                 break;
        case 5:  nameCard = "Epice";
                 break;
        case 6:  nameCard = "Livres";
                 break;
        case 7:  nameCard = "Miel";
                 break;
        case 8:  nameCard = "The";
                 break;
        case 9:  nameCard = "Vin";;
                 break;       
        default: nameCard = "Je ne vous comprends pas. :( ";
                 break;
    }
		return nameCard;
	}
	
	public String aiVendeur(Integer nbPieces, Integer idCard ) {
		String answer, nameCard;
		if ((nbPieces == 0) && (idCard == 0)) {
			answer = "I won’t participate in this part";
		} else {
			nameCard = idCardtoName(idCard);
			answer = "I'm the seller and I'm selling %d pieces and a card, called '" + nameCard + "'";
			answer = String.format(answer, nbPieces);
		}
		return answer;
	}
	
	public String aiAcheteur (Integer idCard, Integer nbPieces) {
		String nameCard = idCardtoName(idCard);
		//if ()
		if (idCard == 0) {
			return "I'm proposing " + nbPieces + " pieces";
		}else {
		return "I'm proposing a card, called '"+ nameCard + "'";
		}
	}
	
	//public void send(String message) {
	//	
	//	writer.println("[AI] "+ message );
	//}
	
	public String theAnswer() {
		Matcher matcher = new Matcher();
	    matcher.addMatcher(new PatternMatcher("1", "Hello", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("2", "Hi, my name is $name1?", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("3", "Hey AI, how are you", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("4", "Great!", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("5", "Hey AI, what are you selling", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("6", "Hey AI, what are you buying", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("7", "Hey AI, what are you going to do", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("8", "Hey AI, who will be the seller", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("9", "bye", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("10", "You are really good in this game", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("11", "What are you $name3*", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("12", "I'm proposing $name4? pieces"));
	    matcher.addMatcher(new PatternMatcher("13", "I will win this game"));
	    matcher.addMatcher(new PatternMatcher("14", "I won’t participate in this part"));
	    matcher.addMatcher(new PatternMatcher("15", "I'm the seller and I'm selling $name4? pieces and a card, called $name5?"));
	    matcher.addMatcher(new PatternMatcher("16", "I'm proposing a card, called $name5?"));

	    
	    String varName = "name1";
	    String resp;
	    if (Question.length() > 0) {
	    	String[] words = toWords(this.Question);
	        TextAnnotation tokens = new TextAnnotation(words.length);
	        MatchResult result = null;
		    for (int i=0; i < words.length;i++){
	    		tokens.addTextToken(new TextToken(words[i]));
	    		result = matcher.match(tokens, 0, tokens.size());
	    	}
			if (result.getMatchedTokens() == -1) {
				resp = "Haha so cool, but actually I didn't understand :( ";
			} else {
				Integer id = Integer.parseInt(result.getTemplateID());
				switch (id) {
		        case 1:  resp = "Hi!";
		                 break;
		        case 2:  resp = "Hi " + resultInVariable(result, varName);
		                 break;
		        case 3:  resp = "I'm fine, thank you! How are you? ";
		                 break;
		        case 4:  resp = "Thanks!";
		                 break;
		        case 5:  resp = aiVendeur(1,2);
		                 break;
		        case 6:  resp = aiAcheteur(8,1);
		                 break;
		        case 7:  resp = "I'm going to beat all of you! Ha- ha! ";
		                 break;
		        case 8:  resp = "The seller will be ";
		                 break;
		        case 9: resp = "Bye!";
		                 break;
		        case 10: resp = "Thank you";
		                 break;
		        case 11: resp = "Let me play, please!";
		                 break;
		        case 12: resp = "Are you okey with that?";
                		 break;
		        case 13: resp = "No way!";
		                 break;
		        case 14: resp = "Ok?";
		                 break;
		        case 15: resp = "Cool? ";
		        		 break;
		        case 16: resp = "Haha, I'm good, right? ";
		        		 break;
		        default: resp = "Je ne vous comprends pas. :( ";
		                 break;
		    }
			}
	    } else {
	    	resp = "hi";
	    }
			return resp;
	    }
	    
}
