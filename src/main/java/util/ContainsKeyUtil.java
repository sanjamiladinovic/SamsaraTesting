package util;

public class ContainsKeyUtil {

	public static boolean containedKeyInPlainText(String text, char c) {

		for (int i = 0; i < text.length(); i++) {

			if (text.charAt(i) == c)

				return true;

		}
		return false;
	}

}
