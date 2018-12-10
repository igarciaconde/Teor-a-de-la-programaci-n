package tp.p2.logic.multigames;
import tp.p2.*;

import java.util.Random;

public interface GameRules {
	
	
	void addNewCellAt(Board board, Position pos, long rand);
	
	int merge(Cell self, Cell other);
	
	boolean canMerge(Cell Self,Cell other);
	
	int getWinValue(Board board);
	
	boolean win(Board board);
	
	int getScoreFromMerge(Cell self);
	
	
///////////////////////////////////////////////////METODOS DEFAULT////////////////////////////////////////////////////////
	
	//INDICA SI SE HA PERDIDO EL JUEGO
	default boolean lose(Board board) {
		return board.fullBoard();
	}
	
	//CREA UN TABLERO DE SIZE X SIZE
	default Board createBoard(int size) {
		Board board = new Board(size);
		return board;
	}
	
	//AÑADE UNA CELDA EN UNA POSICION ALEATORIA EN EL TABLERO
	default void addNewCell(Board board, long rand) {
		
		int i = board.howManyFree();//numero de vacios en el tablero
		Position[]auxP = new Position[i];
		auxP = board.freeCells(i);
		//Barajamos el array de Position
		shuffle(auxP,rand);
		for(int l = 0; l < 1;++l){//añadimos un valor nuevo a una celda
			addNewCellAt(board, auxP[l], rand);
		}
		
	}
	
	//INICIA EL TABLERO CUANDO HYA UNA NUEVA PARTIDA O SE REALIZA UN RESET
	default void initBoard(Board board, int numCells, long rand) {
		//Creamos un array de Position para generar posiciones aleatorias del tablero Board
		Position []aux = new Position[board.getBoardSize()*board.getBoardSize()];
		int k =0;
		for(int i =0; i < board.getBoardSize(); ++i){
			for( int j =0; j < board.getBoardSize() ; ++j){
				aux[k] = new Position(i,j);
				k++;
			}
		}
		shuffle(aux,rand);
		//Damos unos valores iniciales a unas posiciones aleatorias
		for(int l = 0; l < numCells;++l){
					addNewCellAt(board, aux[l], rand);
		}
	}
	
	
////////////////METODOS STATIC/////////////////////////////////////////////////////////
	
	//Barajamos las posiciones
	static Position[] shuffle (Position[] ar, long r) {
		Random auxRandom = new Random(r);
		int posRandom;
		for (int i = 0; i < ar.length; ++i) {
			posRandom = auxRandom.nextInt((ar.length-1 )-(-1));
			swap(ar, i, posRandom);
		}
		return ar;
	}
	
	//Intercambiamos 2 posiciones
	static void swap (Position[] ar,int i, int j) {
			Position aux = ar[i];
			ar[i] = ar[j];
			ar[j] = aux;
		}
}
