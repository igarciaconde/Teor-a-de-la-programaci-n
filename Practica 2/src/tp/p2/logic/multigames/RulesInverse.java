package tp.p2.logic.multigames;

import tp.p2.*;
import java.util.Random;

public class RulesInverse implements GameRules {

//////////////////////////////////////////////////METODOS GENERALES RULESINVERSE////////////////////////////////////////
	
	
	//SETER DE UN VALOR ALEATORIO {1024,2048} EN UNA POSICION DADA
	public void addNewCellAt(Board board, Position pos, long rand) {
		 Random randomValue = new Random(rand);
		 int valor = randomValue.nextInt(100 - 0);
		 if(valor < 90) board.setCell(pos, 2048);
		 else board.setCell(pos, 1024);
	}
	
	//DEVUELVE TRUE SI SE PUEDE FUSIONAR
	public boolean canMerge(Cell self, Cell other) {
		return self.getValue()==other.getValue();
	}
	
	//SI SE PUEDE FUSIONAR DEVUELVE EL VALOR PRODUCIDO POR LA FUSION
	public int merge(Cell self, Cell other) {
		
		if(canMerge(self, other)) {
			int div = self.getValue() / 2;
			return div;
		}
		else return 0;
	}
	
	//DEUELVE TRUE SI EL JUEGO SE HA GANADO
	public boolean win(Board board) {
		
		if (getWinValue(board)== 1)return true;
		else return false;
	}
	
	
///////////////////////////////////////////////GETERS///////////////////////////////////////////////////////////////////

	
	//DEVUELVE EL MEJOR VALOR DEL TABLERO
	public int getWinValue(Board board) {
		
		int minimo=3333;
		Position aux;
		for(int i =0 ;i < board.getBoardSize();i++) {
			for(int j =0; j < board.getBoardSize() ;j++) {
				aux = new Position(i,j);
				if(board.getValue(aux) < minimo && board.getValue(aux)!= 0) {
					minimo = board.getValue(aux);
				}
			}
		}
		return minimo;
	}
	
	//DEVUELVE LA PUNTUACION OBTENIDA DE UNA FUSION
	public int getScoreFromMerge(Cell other) {
		return (2048 / other.getValue());
	}

	


}
