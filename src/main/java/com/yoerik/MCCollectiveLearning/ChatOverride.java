package com.yoerik.MCCollectiveLearning;


import java.util.HashMap;

public class ChatOverride {
	HashMap<String, Boolean> allowedWords = new HashMap<String, Boolean>();
	
	ChatOverride() {
		allowedWords.put("tree", true);
		allowedWords.put("block", true);
		allowedWords.put("you", true);
		allowedWords.put("me", true);
		allowedWords.put("I", true);
		allowedWords.put("water", true);
		allowedWords.put("axe", true);
		allowedWords.put("sword", true);
		allowedWords.put("house", true);
		allowedWords.put("bed", true);
		allowedWords.put("food", true);
		allowedWords.put("mob", true);
		allowedWords.put("yes", true);
		allowedWords.put("no", true);
		allowedWords.put("dirt", true);
		allowedWords.put("stone", true);
		allowedWords.put("glass", true);
		allowedWords.put("wood", true);
		allowedWords.put("here", true);
		allowedWords.put("there", true);
		allowedWords.put("gem", true);
		allowedWords.put("iron", true);
		allowedWords.put("coal", true);
		allowedWords.put("colonialism", true);
		allowedWords.put("baseball", true);
		allowedWords.put("thing", true);
		allowedWords.put("animal", true);
		allowedWords.put("farm", true);
		allowedWords.put("day", true);
		allowedWords.put("night", true);
		allowedWords.put("1", true);
		allowedWords.put("2", true);
		allowedWords.put("3", true);
		allowedWords.put("4", true);
		allowedWords.put("5", true);
		allowedWords.put("6", true);
		allowedWords.put("7", true);
		allowedWords.put("8", true);
		allowedWords.put("9", true);
		allowedWords.put("10", true);
	}
	
	String restrictMessage(String message) {
		String[] msgTokens = message.split(" ");
		String finalMsg = "";
		for (String word : msgTokens) {
			if (allowedWords.get(word)) {
				finalMsg += word + " ";
			}
		}
		return finalMsg;
	}
}
