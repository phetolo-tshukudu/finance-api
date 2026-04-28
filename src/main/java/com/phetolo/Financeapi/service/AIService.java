package com.phetolo.Financeapi.service;



import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import com.phetolo.Financeapi.model.Category;

@Service
public class AIService {
	private OpenAiChatModel model;

	public AIService(OpenAiChatModel model) {
		this.model = model;
	}
	
	
	
	
	public Category categorizeTransaction(String description) {
	    String prompt = """
	        Categorize this transaction into one of:
	        FOOD, TRANSPORT, GROCERIES, ENTERTAINMENT, UTILITIES, SHOPPING, OTHER.
	        Only return one category.
	        Transaction: %s
	        """.formatted(description);

	    try {
	        Category result = Category.valueOf(model.call(prompt).trim().toUpperCase());
	        return validateCategory(result);
	    } catch (Exception e) {
	        return fallbackCategory(description);
	    }
	}

	private Category validateCategory(Category category) {
	    return switch (category) {
	        case  FOOD , TRANSPORT ,  GROCERIES ,  ENTERTAINMENT ,
	              UTILITIES ,  SHOPPING ,  OTHER  -> category;
	        default -> Category.OTHER;
	    };
	}

	private Category fallbackCategory(String description) {
	    String text = description.toLowerCase();

	    if (text.contains("uber") || text.contains("taxi") || text.contains("bolt")) {
	        return Category.TRANSPORT;
	    }
	    if (text.contains("kfc") || text.contains("mcd") || text.contains("restaurant")) {
	        return Category.FOOD;
	    }
	    if (text.contains("checkers") || text.contains("pick n pay") || text.contains("shoprite")) {
	        return Category.GROCERIES;
	    }
	    if (text.contains("electricity") || text.contains("water") || text.contains("wifi")) {
	        return Category.UTILITIES;
	    }

	    return Category.OTHER;
	}
	
}
