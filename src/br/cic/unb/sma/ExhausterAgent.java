package br.cic.unb.sma;

/**
 * Esta classe implementa o comportamento de um aspirador de pó. Para ativar o
 * {@link ExhausterAgent} é necessário ter energia suficiente que é diminuída
 * sempre que o {@link ExhausterAgent} é acionado. Quando o agente muda de sala
 * e encontra sujeira, acrescenta o custo da limpeza do ambiente
 * {@link Environment#cost()} a sua energia.
 * 
 * Quando o agente encontra-se sem energia, ele dorme
 * 
 * @author alessandro
 * 
 */
public class ExhausterAgent extends Agent {

	/**
	 * Cria o aspirador de pó em um ambiente.
	 * 
	 * @param environment
	 */
	public ExhausterAgent(Environment environment) {
		super(environment);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAction() {
		if (this.getEnvironment().isDirty(this.getCurrentPosition())) {
			this.getEnvironment().clean(this.getCurrentPosition());
			this.energy += this.getEnvironment().cost();
		} else {
			this.energy -= this.getEnvironment().cost();
		}
	}

	@Override
	public String getName() {
		return "E";
	}
}
