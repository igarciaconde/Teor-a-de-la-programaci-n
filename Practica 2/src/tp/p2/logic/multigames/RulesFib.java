package tp.p2.logic.multigames;

import tp.p2.*;
import java.util.Random;
import tp.p2.util.*;

public class RulesFib implements GameRules{

	
/////////////////////////////////////METODOS GENERALES DE RULESFIB/////////////////////////////////////////////////////7	
	
	
	//INSERTA UN VALOR ALEATORIO {1,2} EN UNA POSICION DADA
	public void addNewCellAt(Board board, Position pos, long rand) {
		 Random randomValue = new Random(rand);
		 int valor = randomValue.nextInt(100 - 0);
		 if(valor < 90) board.setCell(pos, 1);
		 else board.setCell(pos, 2);
	}
	
	//COMPRUEBA SI DOS CELDAS SE PUEDEN FUSIONAR
	public boolean canMerge(Cell self, Cell other) {
		if(self.getValue() > other.getValue() && self.getValue() == MyMathsUtil.nextFib(other.getValue()))return true;
		else if(other.getValue() > self.getValue() && other.getValue() == MyMathsUtil.nextFib(self.getValue()))return true;
		else if(self.getValue() == 1 && other.getValue()==1)return true;
		else return false;
	}
	
	//SI SEPUEDEN FUSIONAR DEVUELVE EL VALOR DE LA FUSION
	public int merge(Cell self, Cell other) {
		
		if(canMerge(self, other)) {
			if(self.getValue() > other.getValue()) {
				int suma = MyMathsUtil.nextFib(self.getValue());
				return suma;
			}
			else {
				int suma = MyMathsUtil.nextFib(other.getValue());
				return suma;
			}
		}
		else return 0;
	}
	
	//DEVUELVE TRUE SI SE HA GANADO EL JUEGO
	public boolean win(Board board) {
		
		if (getWinValue(board)==144)return true;
		else return false;
	}

	
////////////////////////////////////////////////GETERS///////////////////////////////////////////////////////////////
	
	
	//DEVUELVE EL MEJOR VALOR DEL TABLERO
	public int getWinValue(Board board) {
		int max=1;
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
	
	//DEVUELVE EL VALOR PRODUCIDO POR UAN FUSION EN PUNTOS
	public int getScoreFromMerge(Cell self) {
		return self.getValue();
	}

	


}
