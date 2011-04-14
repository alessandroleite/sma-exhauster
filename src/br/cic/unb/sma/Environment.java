package br.cic.unb.sma;

import java.awt.Point;

import br.cic.unb.sma.util.Maths;

/**
 * @author Alessandro Leite
 */
public class Environment {

	/**
	 * Vetor que representa os locais que compõem o ambiente a ser percorrido
	 * pelos agentes. Utiliza-se 1 (Um) para representar que o ambiente está
	 * sujo e O (Zero) para ambiente limpo.
	 */
	private final int[][] places = new int[2][2];

	/**
	 * Indica os locais que estão ocupados
	 */
	private final String[][] occupation = new String[2][2];

	/**
	 * Retorna <code>true</code> se a posição dada está suja ou não.
	 * 
	 * @return <code>true</code> se o local estiver sujo ou <code>false</code>
	 *         caso contrário.
	 */
	public synchronized boolean isDirty(Point place) {
		return this.places[(int) place.getX()][(int) place.getY()] > 0;
	}

	/**
	 * Suja o local representado pelas coordenadas fornecidas.
	 */
	public synchronized void dirty(Point place) {
		add(place, 1);
		System.err.println("A Sala("
				+ (int) place.getX()
				+ ","
				+ (int) place.getY()
				+ ") estah suja. A quantidade de sujeira eh: "
				+ String.format("%04d",
						this.places[(int) place.getX()][(int) place.getY()]));
	}

	/**
	 * Limpa um dado local.
	 * 
	 * @param place
	 */
	public synchronized void clean(Point place) {
		if (this.places[(int) place.getX()][(int) place.getY()] > 0)
			add(place, -1);
	}

	/**
	 * Retorna a quantidade de locais do ambiente. O primeiro valor representa a
	 * quantidade de andares e o segundo a quantidade de locais do andar.
	 * 
	 * @return o tamanho do ambiente.
	 */
	public int[] size() {
		return new int[] { this.places.length, this.places.length };
	}

	/**
	 * Escolhe e retorna aleatoriamente um local.
	 * 
	 * @return Um local de forma aleatória.
	 */
	public Point room() {
		return new Point(Maths.random(0, this.places.length), Maths.random(0,
				this.places.length));
	}

	/**
	 * Retorna o custo de visitar um determinado local.
	 * 
	 * @return O custo do ambiente.
	 */
	public double cost() {
		return 0.5;
	}

	public synchronized void leave(Point position) {
		this.occupation[(int) position.getX()][(int) position.getY()] = null;
	}

	public synchronized void walkTo(Point position, String name) {
		this.occupation[(int) position.getX()][(int) position.getY()] = name;
	}

	private synchronized void add(Point place, int value) {
		this.places[(int) place.getX()][(int) place.getY()] += value;
	}
}