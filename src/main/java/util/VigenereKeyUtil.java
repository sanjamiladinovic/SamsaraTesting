package util;

public class VigenereKeyUtil {

	private static String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static String repeatString(String key, String plainText) {
		StringBuilder builder = new StringBuilder(plainText.length() + key.length() - 1);// max kapacitet buildera je
																							// ovaj u zagradi, a on se
																							// dole puni
		while (builder.length() < plainText.length()) {
			builder.append(key);
		}
		builder.setLength(plainText.length());// odstranjujem nepotreban visak buidera
		String result = builder.toString();// vracam u string
		return result;
	}

	public static String getEncriptedVigenere(String vigenereKey, String plainText) {
		String encriptedVigenereString = "";
		int indexOfKey = 0;
		int indexOfPlainText = 0;

		if (vigenereKey.length() < plainText.length()) {
			vigenereKey = repeatString(vigenereKey, plainText);
		}

		for (int i = 0; i < vigenereKey.length(); i++) {
			indexOfKey = alphabet.indexOf(vigenereKey.charAt(i));
			indexOfPlainText = alphabet.indexOf(plainText.charAt(i));
			if ((indexOfKey + indexOfPlainText) > alphabet.length())
				encriptedVigenereString += alphabet.charAt((indexOfKey + indexOfPlainText) % alphabet.length());
			else
				encriptedVigenereString += alphabet.charAt(indexOfKey + indexOfPlainText);
		}
		return encriptedVigenereString;

	}

}
