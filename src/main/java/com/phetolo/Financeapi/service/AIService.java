package com.phetolo.Financeapi.service;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class AIService {
	private OpenAiChatModel model;

	public AIService(OpenAiChatModel model) {
		this.model = model;
	}
	
	
	
	
	public String categorizeTransaction(String description) {
	    String prompt = """
	        Categorize this transaction into one of:
	        FOOD, TRANSPORT, GROCERIES, ENTERTAINMENT, UTILITIES, SHOPPING, OTHER.
	        Only return one category.
	        Transaction: %s
	        """.formatted(description);

	    try {
	        String result = model.call(prompt).trim().toUpperCase();
	        return validateCategory(result);
	    } catch (Exception e) {
	        return fallbackCategory(description);
	    }
	}

	private String validateCategory(String category) {
	    return switch (category) {
	        case "FOOD", "TRANSPORT", "GROCERIES", "ENTERTAINMENT",
	             "UTILITIES", "SHOPPING", "OTHER" -> category;
	        default -> "OTHER";
	    };
	}

	private String fallbackCategory(String description) {
	    String text = description.toLowerCase();

	    if (text.contains("uber") || text.contains("taxi") || text.contains("bolt")) {
	        return "TRANSPORT";
	    }
	    if (text.contains("kfc") || text.contains("mcd") || text.contains("restaurant")) {
	        return "FOOD";
	    }
	    if (text.contains("checkers") || text.contains("pick n pay") || text.contains("shoprite")) {
	        return "GROCERIES";
	    }
	    if (text.contains("electricity") || text.contains("water") || text.contains("wifi")) {
	        return "UTILITIES";
	    }

	    return "OTHER";
	}
	
}
