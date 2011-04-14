package br.cic.unb.sma;

import br.cic.unb.sma.util.Maths;

/**
 * Esta classe representa o agente responsável por sujar o ambiente. Esse
 * {@link Agent} quando está sem energia dorme durante um tempo
 * aleatório entre [0,5] segundos e a sua energia nesse caso aumenta uma vez e
 * meia o custo de um movimento do ambiente ({@link Environment}).
 * 
 * @author Alessandro Leite
 */
public class DirtAgent extends Agent {

	public DirtAgent(Environment environment) {
		super(environment);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAction() {
		this.getEnvironment().dirty(getCurrentPosition());
	}

	@Override
	protected void sleep() {
		try {
			Thread.sleep(1000 * Maths.random(1, 5));
			this.energy += this.getEnvironment().cost() * 1.5;
		} catch (InterruptedException ignore) {
		}
	}

	@Override
	public String getName() {
		return "D";
	}
}