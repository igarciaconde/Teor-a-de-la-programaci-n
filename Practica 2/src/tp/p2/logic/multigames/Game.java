package tp.p2.logic.multigames;
import tp.p2.*;
import tp.p2.control.exceptions.EmptyStackException;
import tp.p2.control.exceptions.*;


public class Game {
	
	
//////////////////////////////////////////ATRIBUTOS DE GAME/////////////////////////////////////////////////////////////
	
	
	private Board board;// tablero, objeto de la clase Board
	private int size; // size*size tamaño de tablero
	private int initCells;//celdas iniciales
	private long myRandom;//semilla
	private MoveResults gameResults;//objeto gameResult
	private GameState state;
	private GameStateList ues;//pila de undo
	private GameStateList res;//pila de redo
	private GameRules currentRules;//reglas del juego
	private boolean exit;//condicion de salida directa
	
	
////////////////////////////////////////////CONSTRUCTORA////////////////////////////////////////////////////////////////
	
	
	public Game(int size, int initCells,long seed, GameRules gr) {
		
		this.size = size;
		this.exit = false;
		this.gameResults = new MoveResults(0,false);
		this.initCells = initCells;
		this.myRandom = seed;
		this.currentRules = gr;
		this.board = currentRules.createBoard(this.size);
		this.currentRules.initBoard(this.board, this.initCells, this.myRandom);
		this.state = new GameState(this.board.getState(), this.gameResults.getScore(), this.size);
		
		this.ues = new GameStateList();
		this.res = new GameStateList();
		
	}

////////////////////////////////////////////METODO NEW GAME////////////////////////////////////////////////////////////
	
public void newGame(int size, int initCells,long seed, GameRules gr) {
		
		this.size = size;
		this.exit = false;
		this.gameResults = new MoveResults(0,false);
		this.initCells = initCells;
		this.myRandom = seed;
		this.currentRules = gr;
		this.board = currentRules.createBoard(this.size);
		this.currentRules.initBoard(this.board, this.initCells, this.myRandom);
		this.state = new GameState(this.board.getState(), this.gameResults.getScore(), this.size);
		
		this.ues = new GameStateList();
		this.res = new GameStateList();
		
	}
	
	
//////////////////////////////////////////////////////SETERS///////////////////////////////////////////////////////////
	
	//RECOGE UNAS REGLAS Y LAS IMPLANTA
	public void setGameRules(GameRules gr){
		this.currentRules = gr;
	}
	
	//Recoge el ultimo estado de juego guardado y lo carga
	public void setState(GameState aState) {
		this.board.setState(aState.getBoardState());
		this.gameResults.setScore(aState.getScore());
		this.state = this.getState();
	}
	
	//nos indica si queremos salir del juego o no
	public void setExit() {
		this.exit = true;
	}


///////////////////////////////////////////////////////GETERS//////////////////////////////////////////////////////////
	

	//PARAMETRO DE SALIDA
	public boolean getExit() {
		return this.exit;
	}
	
	//Devuelve el estado de juego actual para guardarlo
	public GameState getState() {
		GameState aux = new GameState(this.board.getState(),this.gameResults.getScore(),this.size);
		return aux;
	}
		
	//devuelve el valor mas alto del tableo
	public int getWinValue() {
		return this.currentRules.getWinValue(this.board);
	}
	
	//devuelve si se ha hecho movimiento o no
	public boolean getResults() {
		return this.gameResults.getMovement();
	}
	

	
/////////////////////////////////////////METODOS MOVE Y RESET///////////////////////////////////////////////////////////
	
	
	//Hace que el board realice un movimiento (llama a executeMove)
	public void move(Direction dir)throws GameOverException {
		MoveResults aux = new MoveResults(0,false); 	//objeto auxiliar para recibir los resultados del movimiento
		aux = this.board.executeMove(dir,this.currentRules);//Obtenemos el Score y el highest en un auxiliar
		
		
		//Modificamos el moveResults de Game si hay que hacerlo
		this.gameResults.setScore(this.gameResults.getScore() + aux.getScore());
		this.gameResults.setMovement(aux.getMovement());
		
		
		//Si se ha hecho un movimiento, se da un valor a una Cell vacía
		if(this.gameResults.getMovement() == true){
			this.ues.push(this.state);
			this.res.setCuantosCero();
			this.currentRules.addNewCell(this.board, this.myRandom);
			this.state = this.getState();
		}
		if(this.gameO()) throw new GameOverException();
		
	}
	
	
	//Reinicia el tablero con los datos de entrada del pricipio del progama
	public void reset() {
		this.newGame(this.size,this.initCells, this.myRandom, this.currentRules);

	}
	
///////////////////////////////////////////////METODOS DE IMPRESION/////////////////////////////////////////////////////
	
	
	//Sacar en pantalla el Game
	public void printG(){
		
		this.board.print();
		System.out.print("\n");
		System.out.print("mejor: " + Integer.toString(this.getWinValue()) + "       score: " + Integer.toString(this.gameResults.getScore()) + "\n");
	}
	
	
////////////////////////////////////////////////METODOS DE SALIDA//////////////////////////////////////////////////////
	
	
	//Comprueba que el juego es un game over (no hay movimientos ni fusiones posibles)
	public boolean gameO() {
		return this.currentRules.lose(this.board) && this.board.noMerge(this.currentRules);
	}
	
	//Compreba si alguna Cell == 2048
	public boolean winG() {
		return this.currentRules.win(this.board);
	}
		
	
	
//////////////////////////////////////////////////METODOS DE PILA///////////////////////////////////////////////////////
	
	//Deshacer movimiento
	public void undo() throws EmptyStackException {
		GameState aux = this.state;
		this.setState(this.ues.pop());
		this.res.push(aux);
		System.out.println("Undoing movement...");
	
	}
	
	//Rehacer movimiento
	public void redo() throws EmptyStackException {
		GameState aux = this.state;
		this.setState(this.res.pop());
		this.ues.push(aux);
		System.out.println("Redoing movement...");
	}
			
	
	
}
