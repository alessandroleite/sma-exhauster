package br.cic.unb.sma.util;

public class Console {

	/**
	 * Limpa o console do computador.
	 */
	public static void clear() {
		String cmd = null;
		try {
			if (System.getProperty("os.name").startsWith("Windows"))
				cmd = "cls";
			else
				cmd = "clear";
			Runtime.getRuntime().exec(cmd);
		} catch (Exception ignore) {
		}
	}
}
