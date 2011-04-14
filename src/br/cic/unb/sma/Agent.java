package br.cic.unb.sma;

import java.awt.Point;

import br.cic.unb.sma.util.Maths;
import br.cic.unb.sma.util.ThreadUtils;

/**
 * Esta classe representa um agente autônomo que tendo energia visita um
 * determinado ambiente ({@link Environment}). A cada movimento o agente paga um
 * preço representado pela perda de energia. O agente acumula energia quando
 * realiza a ação que foi programado. Se a energia chegar a zero o {@link Agent}
 * entra no estado de dormência e nele permanece por um determinado intervalo de
 * tempo determinado aleatoriamente entre [1,10] segundos.
 * 
 * @author Alessandro Leite
 * 
 */
public abstract class Agent implements Runnable {

	/**
	 * Ambiente o qual o {@link Agent} pode percorrer.
	 */
	private Environment environment;

	/**
	 * Representa a posição atual do {@link Agent}
	 */
	private Point currentPosition;

	/**
	 * Nível da energia disponível ao agente. Compreendido entre [0, +infinity]
	 */
	protected double energy;

	/**
	 * Representa se um agente está vivo ou morto. Um agente sem energia não
	 * significa que ele esteja morto.
	 */
	private boolean live;

	/**
	 * Cria um {@link Agent} e posiciona-o em algum lugar do ambiente. A energia
	 * inicial do {@link Agent} é o suficiente para ele realizar um movimento no
	 * ambiente.
	 * 
	 * @param environment
	 */
	public Agent(final Environment environment) {
		this.environment = environment;
		this.currentPosition = this.environment.room();
		this.energy = this.environment.cost();
		this.live = true;

		System.err.println("Agente " + getName()
				+ " criado com nivel de energia igual a " + getEnergy() + " "
				+ "na sala (" + (int) getCurrentPosition().getX() + ", "
				+ (int) getCurrentPosition().getY() + ")");
	}

	/**
	 * Representa o comportamento do {@link Agent}, ou seja, o seu objetivo.
	 */
	public abstract void doAction();

	/**
	 * Retorna no nome desse {@link Agent}
	 * 
	 * @return Nome do agente.
	 */
	public abstract String getName();

	/**
	 * Mata esse {@link Agent}.
	 */
	public void kill() {
		this.live = !live;
	}

	@Override
	public void run() {
		while (live) {
			if (energy >= this.environment.cost()) {
				this.move();
				this.doAction();
				printEnergyLevel();
			} else {
				sleep();
			}
			ThreadUtils.sleep(1000);
		}
	}

	/**
	 * @return the currentPosition
	 */
	public Point getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @return the environment
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * @return the energy
	 */
	public double getEnergy() {
		return energy;
	}

	/**
	 * Move o {@link Agent} para alguma posição do ambiente ({@link Environment}
	 * ) diferente da que ele se encontra. Cada movimento implica em perda de
	 * energia. A quantidade de energia perdida é determinada pelo custo do
	 * movimento no ambiente {@link Environment#cost()}
	 */
	protected void move() {
		int[] size = this.environment.size();
		Point newPosition = new Point(Maths.random(0, size[0]), Maths.random(0,
				size[0]));

		if (!newPosition.equals(this.currentPosition)) {
			this.environment.leave(this.currentPosition);
			this.currentPosition = newPosition;
			this.environment.walkTo(newPosition, this.getName());
			printPosition();
		}
	}

	/**
	 * Coloca esse {@link Agent} no estado de dormência compreendido entre [1,
	 * 10] segundos.
	 */
	protected void sleep() {
		int time = Maths.random(1, 10);
		System.err.println("O agente " + getName()
				+ " estah sem energia e tera que durmir por " + time
				+ " segundo(s) para recarregar.");
		ThreadUtils.sleep(1000 * time);
		this.energy += this.environment.cost();
	}

	private void printEnergyLevel() {
		System.err.println("O agente " + getName()
				+ " estah com nivel de energia igual a: " + this.energy);
	}

	private void printPosition() {
		System.err.println("O agente " + getName() + " estah na sala ("
				+ (int) getCurrentPosition().getX() + ", "
				+ (int) getCurrentPosition().getY() + ")");
	}
}
