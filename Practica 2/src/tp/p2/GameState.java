package tp.p2;

public class GameState {

////////////////////////////////////ATRIBUTOS GAMESTATE/////////////////////////////////////////////////////////////////
	private int [][] boardState; //tablero de enteros que representa el board
	private int score;//atributo score que representa la puntuacion acutal
	private int boardStateSize;//tamaño del tablero a crear
	
///////////////////////////////////CONSTRUCTORA/////////////////////////////////////////////////////////////////////////
	
	//este metodo crea un GameState que contiene un board de enteros y una puntuacion para representar el estado del tablero
	public GameState(int [][] board, int score, int boardSize) {
		this.boardStateSize = boardSize;
		this.boardState = new int [this.boardStateSize][this.boardStateSize];
		for(int i =0; i < this.boardStateSize;i++) {
			for(int j=0; j < this.boardStateSize; j++) {
				this.boardState[i][j] = board[i][j];
			}
		}
		this.score = score;
		
	}

//////////////////////////////////METODOS GETER//////////////////////////////////////////////////////////////////////////

	//geter del atributo score
	public int getScore() {
		return this.score;
	}
	
	//geter del atributo BoardState
	public int [][] getBoardState() {
		return this.boardState;
	}
	



	
	
}
