package com.yoerik.MCCollectiveLearning;


import java.util.HashMap;

public class ChatOverride {
	private static HashMap<String, Boolean> allowedWords = new HashMap<String, Boolean>();
	static {
		allowedWords.put("tree", true);
		allowedWords.put("block", true);
		allowedWords.put("you", true);
		allowedWords.put("me", true);
		allowedWords.put("i", true);
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
	}
	
	static String restrictMessage(String message) {
		String finalMsg = "";
		String[] splitted = message.split(" ");
		for(int i = 0; i < splitted.length; i++) {
			String word = splitted[i];
			if (allowedWords.get(word.toLowerCase()) != null) {
				finalMsg += word + " ";
			} else if (word.matches("-?\\d+(.\\d+)?")){
				finalMsg += word + " ";
			}
		}
		return finalMsg;
	}
}
