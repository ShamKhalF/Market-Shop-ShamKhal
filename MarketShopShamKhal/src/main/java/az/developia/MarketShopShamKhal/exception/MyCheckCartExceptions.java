package az.developia.MarketShopShamKhal.exception;

import java.util.List;

public class MyCheckCartExceptions extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	private List<String> messages;
	    
	    public MyCheckCartExceptions(List<String> messages) {
	        // super(messages.stream().collect(Collectors.joining(", ")));
	        this.messages = messages;
	    }
	    
	    public List<String> getMessages() {
	        return messages;
	    }
	    
}
