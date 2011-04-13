package br.cic.unb.sma.util;

public class StringUtils {

	public static String emptyLength(String text, int length) {
		if (text == null)
			return emptyLength(" ", length);

		if (text.length() == length)
			return text;

		StringBuilder sb = new StringBuilder(text);
		int diff = length - text.length();

		for (int i = 0; i < diff; i++)
			sb.append(" ");

		return sb.toString();
	}
}
