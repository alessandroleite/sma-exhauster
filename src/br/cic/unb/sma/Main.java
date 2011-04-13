package br.cic.unb.sma;


public class Main {
	
	static ExhausterAgent createExhauter(Environment environment){
		return new ExhausterAgent(environment);
	}
	
	public static void main(String[] args) {
		
		final Environment environment = new Environment();
		ExhausterAgent exhauster = createExhauter(environment);
		DirtAgent dirt = new DirtAgent(environment);
		
		new Thread(dirt).start();
		new Thread(exhauster).start();
	}
}
