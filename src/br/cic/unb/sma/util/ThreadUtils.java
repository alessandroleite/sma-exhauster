package br.cic.unb.sma.util;

public class ThreadUtils {
	
	public static void sleep(long timeout){
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException ignore) {
		}
	}
}
