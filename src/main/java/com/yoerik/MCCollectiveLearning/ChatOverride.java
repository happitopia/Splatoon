package com.yoerik.MCCollectiveLearning;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ChatOverride {
	private static final Set<String> allowedWords = new HashSet<String>(Arrays.asList(
			new String[] {
		"tree", "block", "you", "me", "i", "water", "axe", 
		"sword", "house", "bed", "food", "mob", "yes", "no", 
		"dirt", "stone", "glass", "wood", "here", "there", 
		"gem", "iron", "coal", "colonialism", "baseball", 
		"thing", "animal", "farm", "day", "night"
		}));
	
	static String restrictMessage(String message) {
		String finalMsg = "";
		String[] splitted = message.split(" ");
		for(int i = 0; i < splitted.length; i++) {
			String word = splitted[i];
			if (allowedWords.contains(word.toLowerCase())) {
				finalMsg += word + " ";
			} else if (word.matches("-?\\d+(.\\d+)?")){
				finalMsg += word + " ";
			}
		}
		return finalMsg;
	}
}
