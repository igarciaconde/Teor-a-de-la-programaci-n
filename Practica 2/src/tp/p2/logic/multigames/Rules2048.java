package tp.p2.logic.multigames;

import tp.p2.*;
import java.util.Random;

public class Rules2048 implements GameRules {

	
///////////////////////////////////////////METODOS GENRALES RULES2048////////////////////////////////////////////////////
	
	//ESTABLECE EN UNA POSICION POS UN VALOR ALEATORIO {2,4}
	public void addNewCellAt(Board board, Position pos, long rand) {
		 Random randomValue = new Random(rand);
		 int valor = randomValue.nextInt(100 - 0);
		 if(valor < 90) board.setCell(pos, 2);
		 else board.setCell(pos, 4);
	}
	
	//DEVUELVE TRUE SI DOS CELDAS SE PUEDEN FUSIONAR
	public boolean canMerge(Cell self, Cell other) {
		return self.getValue()==other.getValue();
	}
	
	//SI SE PUEDEN FUSIONAR DEVUELVE EL VALOR DE LA FUSION
	public int merge(Cell self, Cell other) {
		if(canMerge(self, other)) {
			int suma = self.getValue() + other.getValue();
			return suma;
		}
		else return 0;
	}
	
	//DEVUELVE TRUE SI SE HA GANDO EL JUEGO
	public boolean win(Board board) {
		if (getWinValue(board)==2048)return true;
		else return false;
		
	}
	
//////////////////////////////////////////////////////GETERS///////////////////////////////////////////////////////////
	
	
	//DEVUELVE LA PUNTUACION PRODUCIDA POR UNA FUSION (YA EN LA CELDA FUSIONADA)
	public int getScoreFromMerge(Cell self) {
		return self.getValue();
	}
	
	//DEVUELVE EL MEJOR VALOR DEL TABLERO
	public int getWinValue(Board board) {
		int max=2;
		Position aux;
		for(int i =0 ;i < board.getBoardSize();i++) {
			for(int j =0; j < board.getBoardSize() ;j++) {
				aux = new Position(i,j);
				if(board.getValue(aux) > max) {
					max = board.getValue(aux);
				}
			}
		}
		return max;
	}

	
	

}
