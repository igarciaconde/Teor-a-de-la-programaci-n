package tp.pr1;
import java.util.Random;

public class Game {
	private Board board;// tablero, objeto de la clase Board
	private int size; // size*size tamaño de tablero
	private int initCells;//celdas iniciales
	private long myRandom;//semilla
	private MoveResults gameResults;//objeto gameResult
	
	//La constructora
	public Game(int size, int initCells,long seed) {
		
		this.size = size;
		this.board = new Board(this.size);
		this.gameResults = new MoveResults(0,0,false);
		this.initCells = initCells;
		this.myRandom = seed;
		//Creamos un array de Position para generar posiciones aleatorias del tablero Board
		Position []aux = new Position[this.size*this.size];
		int k =0;
		for(int i =0; i < this.size; ++i){
			for( int j =0; j < this.size ; ++j){
				aux[k] = new Position(i,j);
				k++;
			}
		}
		//Barajamos el array Position[] para obtener posiciones aleatorias
		shuffle(aux,this.myRandom);
		
		//Damos unos valores iniciales a unas posiciones aleatorias
		for(int l = 0; l < this.initCells;++l){
			this.board.setCell(aux[l], setRandom());
			//Iniciamos el highest con el valor más alto
			if(this.gameResults.getHighest() < this.board.getValue(aux[l])) this.gameResults.setHigest(this.board.getValue(aux[l]));
		}
		
		
	}
	
	//Comprueba que el juego es un game over (no hay movimientos ni fusiones posibles)
	public boolean gameO() {
		if(this.board.fullBoard() && this.board.noMerge()) return true;
		else return false;
	}
	
	//Compreba si alguna Cell == 2048
	public boolean winG() {
		return this.board.win();
	}

	//Hace que el board haga un movimiento (llama a executeMove)
	public void move(Direction dir) {
		MoveResults aux = new MoveResults(0,0,false); 	//objeto auxiliar para recibir los resultados del movimiento
		aux = this.board.executeMove(dir);				//Obtenemos el Score y el highest en un auxiliar
		//Modificamos el moveResults de Game si hay que hacerlo
		if(aux.getHighest() > this.gameResults.getHighest())this.gameResults.setHigest(aux.getHighest());
		this.gameResults.setScore(this.gameResults.getScore() + aux.getScore());
		this.gameResults.setMovement(aux.getMovement());
		//Si se ha hecho un movimiento, se da un valor a una Cell vacía
		if(this.gameResults.getMovement() == true){
			int i = this.board.howManyFree();//numero de vacios en el tablero
			Position[]auxP = new Position[i];
			auxP = this.board.freeCells(i);
			//Barajamos el array de Position
			shuffle(auxP,this.myRandom);
			for(int l = 0; l < 1;++l){//añadimos un valor nuevo a una celda
				this.board.setCell(auxP[l], setRandom());
			}
		}
		
	}
	
	//Reinicia el tablero con los datos de entrada del pricipio del progama
	public void reset() {
		this.gameResults = new MoveResults(0,0,false);
		Position []aux = new Position[this.size*this.size];
		int k =0;
		//Creamos un Position[] auxiliar para crear unas Cell con un valor concreto
		for(int i =0; i < this.size; ++i){
			for( int j =0; j < this.size ; ++j){
				aux[k] = new Position(i,j);
				this.board.setCell(aux[k], 0);
				k++;
			}
		}
		//Barajamos
		shuffle(aux,this.myRandom);
		//Establecemos el valor
		for(int l = 0; l < this.initCells; ++l){
			this.board.setCell(aux[l], setRandom());
		}
	}
	
	//Barajamos las posiciones
	public Position[] shuffle (Position[] ar, long r) {
		Random auxRandom = new Random(r);
		int posRandom;
		for (int i = 0; i < ar.length; ++i) {
			posRandom = auxRandom.nextInt((ar.length-1 )-(-1));
			swap(ar, i, posRandom);
		}
		return ar;
	}
	
	//Intercambiamos 2 posiciones
	public void swap (Position[] ar,int i, int j) {
		Position aux = ar[i];
		ar[i] = ar[j];
		ar[j] = aux;
	}
	
	//Devolvemos un entero igual a 2 o igual a 4  
	public int setRandom(){
		int randomValue;
		 Random r = new Random();
		 randomValue = r.nextInt(100 - 1) + 1;
		 if(randomValue < 90) return 2;
		 else return 4;
	}
	
	//Sacar en pantalla el Game
	public void printG(){
		
		this.board.print();
		System.out.print("\n");
		System.out.print("highest: " + Integer.toString(this.gameResults.getHighest()) + "       score: " + Integer.toString(this.gameResults.getScore()) + "\n");
	}
}
