package tp.p2;
import tp.p2.control.exceptions.*;

public class GameStateList {
	
	
////////////////////////////////////ATRIBUTOS DE LA CLASE GAMESTATELIST//////////////////////////////////////////////////
	
	private GameState [] estados;//pila de estados GameState
	private static final int CAPACITY = 20;//tamaño de la pila
	private int cuantos;// contador de huecos ocupados en la pila
	

///////////////////////////////////CONSTRUCTORA///////////////////////////////////////////////////////////////////////////
	public GameStateList() {
		this.estados = new GameState[CAPACITY];
		this.cuantos = 0;
	}
	
	
//////////////////////////////////METODOS DE PILA/////////////////////////////////////////////////////////////////////////
	
	//este metodo devuelve el ultimo estado almacenado en la pila
	public GameState pop() throws EmptyStackException  {
		// que devuelve el último estado almacenado,
		if(this.isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			int cont = this.cuantos;
			this.cuantos--;
			return this.estados[cont - 1];
		}
	}
	
	//este metodo añade a la pila una estado 
	public void push(GameState state) {
		// almacena un nuevo estado,
		if(this.cuantos==CAPACITY) {
			for(int i =0; i < CAPACITY - 1; i++) {
				this.estados[i] = this.estados[i + 1];
			}
			this.estados[CAPACITY - 1]= state;
		}
		else if(this.cuantos < CAPACITY) {
			this.estados[cuantos] = state;
			this.cuantos++;
		}
	}
	
	//este metodo devuelve un booleano a true si la pila esta vacia
	public boolean isEmpty(){
		return this.cuantos==0;
	}
	
//////////////////////////////GETER////////////////////////////////////////////////////////////////////////////////////
	
	public int getCuantos() {
		return this.cuantos;
	}
	
///////////////////////////////SETER///////////////////////////////////////////////////////////////////////////////////
	
	public void setCuantosCero() {
		this.cuantos=0;
	}
	
	

	
}
