package util;

public class ReverseStringAndPalindromUtil {

	public static String reverseText(String text) {
		String reversedText = "";
		for (int i = text.length() - 1; i >= 0; i--) {

			reversedText = reversedText + text.charAt(i);

		}

		return reversedText;

	}

	public static boolean isPalindrome(String word) {
		int numOfChars = word.length();
		for (int i = 0; i < (numOfChars / 2); ++i) {
			if (word.charAt(i) != word.charAt(numOfChars - i - 1)) {// oduzima pozadi broj koji je vec predjen napred i
																	// jos -1, zbog razlike u num.indesa i duzine
				return false;
			}
		}

		return true;
	}

}
