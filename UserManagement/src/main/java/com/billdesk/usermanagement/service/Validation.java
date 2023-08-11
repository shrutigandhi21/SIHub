package com.billdesk.usermanagement.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	 private static final String SPECIAL_CHARACTERS_REGEX = "[^a-zA-Z0-9 ]";
	
	boolean checkSpecialCharacters(String text) {
		Pattern pattern = Pattern.compile(SPECIAL_CHARACTERS_REGEX);
       
       // Create a matcher with the input name
       Matcher matcher = pattern.matcher(text);

       // Return true if the pattern is found in the name (i.e., special characters are present)
       return matcher.find();
	}

}