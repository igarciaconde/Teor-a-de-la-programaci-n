package tp.pr1;

public class MoveResults {

	private int score;//Puntuacion obtenida por fusiones
	private int highest;//Valor mas alto obtenido
	private boolean movement;//Boleano que nos indica si un movimiento se ha realizado correctamente
	
	//Constructora
	public MoveResults (int score, int highest, boolean movement) {
		this.score = score;
		this.highest = highest;
		this.movement = movement;
	}
	
	//Metodo getter del atributo score
	public int getScore() {
		return this.score;
	}
	//Metodo getter del atributo highest
	public int getHighest() {
		return this.highest;
	}
	//Metodo getter del atributo movement
	public boolean getMovement() {
		return this.movement;
	}
	//Metodo setter del atributo score
	public void setScore(int auxScore) {
		this.score = auxScore;
	}
	//Metodo setter del atributo highest
	public void setHigest(int auxHigh) {
		this.highest = auxHigh;
	}
	//Metodo setter del atributo movement
	public void setMovement(boolean movement) {
		this.movement = movement;
	}
}
