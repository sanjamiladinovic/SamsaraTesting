package util;

import java.util.ArrayList;
import java.util.List;

public class SplitLettersFromDigitsUtil {
	public static String alphanumeric = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	// public static char[] alphanumericChar=alphanumeric.toCharArray();
	static List<Character> findedLetters = new ArrayList<>();
	static List<Character> findedDigits = new ArrayList<>();

	public static void extractLettersAndDigitsFromPlainText(String plaintext) {
		for (int i = 0; i < plaintext.length(); i++) {

			if (alphanumeric.indexOf(plaintext.charAt(i)) != -1) {
				if ((plaintext.charAt(i) >= 'a' && plaintext.charAt(i) <= 'z')
						|| (plaintext.charAt(i) >= 'A' && plaintext.charAt(i) <= 'Z')) {

					findedLetters.add(plaintext.charAt(i));
				} else {
					findedDigits.add(plaintext.charAt(i));
				}
			}
			
		}
	}

	public static void resetResults() {
		findedLetters = new ArrayList<>();
		findedDigits = new ArrayList<>();
	}
	
	public static List<Character> getFindedLetters() {
		return findedLetters;
	}

	public static List<Character> getFindedDigits() {
		return findedDigits;
	}



	/*
	 * public static char getCharIfExistInPlainText(String plaintext, char oneChar)
	 * {
	 * 
	 * for (int i = 0; i < plaintext.length(); i++) {
	 * 
	 * if (plaintext.charAt(i) == oneChar) { return oneChar; } }
	 * 
	 * return '\u0000';
	 * 
	 * }
	 */

}
