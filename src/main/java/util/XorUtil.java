package util;

public class XorUtil {

	private static String getBinaryPlainText(String plaintext) {

		byte[] bytes = plaintext.getBytes();
		StringBuilder binaryPlaintext = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binaryPlaintext.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}

		}

		String plainTextBinaryResult = binaryPlaintext.toString();

		return plainTextBinaryResult;
	}

	private static String getBinaryVigenereKey(String vigenereKey, String plaintext) {

		StringBuilder binaryVKey = new StringBuilder(plaintext.length() + vigenereKey.length() - 1);
		while (binaryVKey.length() < plaintext.length()) {

			binaryVKey.append(vigenereKey);
		}
		binaryVKey.setLength(plaintext.length());

		String buildResultKey = binaryVKey.toString();

		byte[] bytes = buildResultKey.getBytes();
		StringBuilder binaryVigenereKey = new StringBuilder();

		for (byte b : bytes) {

			int val = b;
			for (int i = 0; i < 8; i++) {
				binaryVigenereKey.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;

			}

		}
		String vigenereKeyBinary = binaryVigenereKey.toString();
		return vigenereKeyBinary;
	}

	public static String plaintextXorVigenereKey(String vigenereKey, String plaintext) {
		String binaryPlaintext = getBinaryPlainText(plaintext);
		String binaryVigenereKey = getBinaryVigenereKey(vigenereKey, plaintext);

		StringBuilder xorBulder = new StringBuilder();// binarni XOR builder od binarnih vigenera i plaintexta

		for (int i = 0; i < binaryPlaintext.length(); i++)
			xorBulder.append(binaryPlaintext.charAt(i) ^ binaryVigenereKey.charAt(i));

		String xorString = xorBulder.toString();

		StringBuilder binaryToAscii = new StringBuilder();// binarni XOR prevodi u textualni ASCII
		for (int i = 0; i < xorString.length(); i += 8) {

			int num = Integer.parseInt(xorString.substring(i, i + 8), 2);
			char letter = (char) num;
			binaryToAscii.append(letter);
		}
		String binaryToAsciiString = binaryToAscii.toString();
		return binaryToAsciiString;

	}

	/*
	 * public static void main(String[] args) {
	 * System.out.println(getBinaryPlainText("HCy8XN"));
	 * System.out.println(getBinaryVigenereKey("sam", "HCy8XN"));
	 * System.out.println(plaintextXorVigenereKey("sam", "HCy8XN"));
	 * 
	 * }
	 */
}
