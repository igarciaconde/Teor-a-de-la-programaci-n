package tp.p2;
import tp.p2.logic.multigames.*;
public class Cell {
	private int value;
	
	//Constructora
	public Cell (int value) {
		this.value = value;
	}
	
	//Getter
	public int getValue() {
		return this.value;
	}
	
	//Setter
	public void setValue (int newValue) {
		this.value = newValue;
	}
	
	//Comprueba si 2 casillas (this y neigbhour) se pueden fusionar
	public boolean canMerge(Cell neighbour, GameRules gr){
		return gr.canMerge(this,neighbour);
	}
	
	//Hace la fusión si se puede
	public boolean doMerge(Cell neighbour, GameRules gr) {
		if (this.canMerge(neighbour, gr)) {	//Se pueden fusionar
			this.setValue(gr.merge(this, neighbour));
			neighbour.setValue(0);
			return true;
		}
		else	//No se ha podido fusionar
			return false;
	}
	
	//Una Cell es vacía si value == 0
	public boolean isEmpty(){
		return this.value == 0;
	}

}
