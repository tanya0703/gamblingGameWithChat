package chat;

import org.syn.n.bad.annotation.TextAnnotation;
import org.syn.n.bad.annotation.TextToken;
import org.syn.n.bad.pattern.MatchResult;
import org.syn.n.bad.pattern.Matcher;
import org.syn.n.bad.pattern.PatternMatcher;

public class Response {
	private String Question;
	
	public Response(String Question) {
		this.Question = Question;
	}
	
	public String[] toWords(String expression){
        String[] words = expression.split(" ");
        return words;
	}
	
	//public String[] findSynonyms(String Word) {
		
	//}
	
	public MatchResult findExpression(String expression) {
		Matcher matcher = new Matcher();
	    matcher.addMatcher(new PatternMatcher("1", "Hello!", "lang=fr; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("3", "How are you?", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("4", "How much money can you pay for that?", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("5", "How many cards can you propose for that?", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("6", "I'm proposing $name3? cards.", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("7", "I'm proposing $name1? card.", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("8", "Bye", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("9", "You are really good in this game", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("10", "What are you $name3*", "lang=en; mood=polite"));
	    matcher.addMatcher(new PatternMatcher("11", "I will win this game! "));
	    
	    String[] words = toWords(expression);
        TextAnnotation tokens = new TextAnnotation(words.length);
        MatchResult result = null;
	    for (int i=0; i < words.length;i++){
    		tokens.addTextToken(new TextToken(words[i]));
    		System.out.println(tokens.getTokens());
    		result = matcher.match(tokens, 0, tokens.size());
    	}
	    
	    return result;
	}
	
	public Integer idResult(MatchResult mr) {
		return Integer.parseInt(mr.getTemplateID());
	}
	
	public String theResponse() {
		Integer id = idResult(findExpression(this.Question));
		String resp;
		switch (id) {
        case 1:  resp = "Hi!";
                 break;
        case 2:  resp = "February";
                 break;
        case 3:  resp = "March";
                 break;
        case 4:  resp = "April";
                 break;
        case 5:  resp = "May";
                 break;
        case 6:  resp = "June";
                 break;
        case 7:  resp = "July";
                 break;
        case 8:  resp = "August";
                 break;
        case 9:  resp = "Thank you!";
                 break;
        case 10: resp = "October";
                 break;
        case 11: resp = "No way! ";
                 break;
        case 12: resp = "December";
                 break;
        default: resp = "Je ne vous comprends pas. :( ";
                 break;
    }
		return resp;
	}
	
}
