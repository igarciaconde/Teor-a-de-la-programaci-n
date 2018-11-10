package tp.pr1;

import tp.pr1.util.MyStringUtils;

public class Board {

	private int boardSize;// Tamaño del tablero
	private Cell [][] board; // Matriz de objetos Cell
	private MoveResults results; //Objeto que guarda los resultados por movimiento
	
	//Constructora
	public Board(int boardSize) {
		this.boardSize = boardSize;
		this.board= new Cell[this.getBoardSize()][this.getBoardSize()];
		for(int i = 0; i < this.getBoardSize(); i++) {
			for( int j = 0; j < this.getBoardSize();j++) {
				this.board [i][j] = new Cell(0); // Creamos los objetos Cell por cada una de las celdas de la matriz
			}
		}
	}
	
	//Metodo setter de la clase board( utiliza in objeto Position y un entero para darle valor a una celula)
	public void setCell(Position pos, int value) {
		 this.board[pos.getFil()][pos.getCol()].setValue(value);
	 }
	 //Metodo getter que decuelve el valor de las celulas del tablero
	public int getValue(Position aux) {
		 return this.board[aux.getFil()][aux.getCol()].getValue();
	 }
	//Metodo getter que devuelve el tamaño del tablero
	public int getBoardSize() {
		return this.boardSize;
	}
	
//// 
	
	//Metodo que mueve las celulas y las fusiona
	public MoveResults executeMove(Direction dir) {
		
		Position pos,posN; // la posicion actual pos, y su vecino posN      
		int auxI = 0;//determina el recorrido de la matriz
		int auxJ = 0;//determina el recorrido de la matriz
		int cellMoved =0;// celdas movidas o fusionadas
		this.results = new MoveResults(0,0,false);//objeto MoveResults
		
		for(int i= 0; i < this.getBoardSize() ; i++) {
			
			//creamos un entero para representar la posicion de la ultima fusion 
			int alreadyMerge = -1;
			
			for(int j = 0; j < this.getBoardSize(); j++) {
				
				switch (dir) { 
				// Aquí se decidira de que manera se recorre la matriz para que no haga falta repetir código
				// En caso de el movimiento hacia arriba e izquierda recorrera incrementando los contadores de 0 a boardSize
				// En los casos restantes de boardSize a 0
				// Tambien en los casos verticales utilizara los indices de forma inversa para trabajar directamente sobre las columnas
				case UP: 
					auxI = j; auxJ=i;
					break;
				case DOWN: 
					auxI = (this.getBoardSize() - 1) - j; auxJ = i;
					break;
				case LEFT:
					auxI = i; auxJ=j;
					break;
				case RIGHT:
					auxI = i; auxJ = (this.getBoardSize() - 1) - j;
					break;
				}
				//Si la celula esta vacia 
				if(!this.board[auxI][auxJ].isEmpty()) {
					
					//Creamos objeto Position con los indices actuales
					pos = new Position(auxI,auxJ);
					//Objeto vecino al objeto ya creado
					posN = pos.neighbour(dir,this.getBoardSize());
					
					//Mientras el vecino no sea == null lo moveremos por el tablero y si se fusiona le daremos valor null para que no siga fusionandose
					while(posN != null) {
						
						//si no hemos fusionado ya en esa posicion y podemos fusionar, fusionamos
						if(posN.getCol()!= alreadyMerge  &&  this.board[posN.getFil()][posN.getCol()].canMerge(this.board[pos.getFil()][pos.getCol()])) {
								
							this.board[posN.getFil()][posN.getCol()].doMerge(this.board[pos.getFil()][pos.getCol()]);//Fusion
							this.results.setScore(this.results.getScore() + this.board[posN.getFil()][posN.getCol()].getValue());//Actualizamos score
							if(this.results.getHighest()< this.board[posN.getFil()][posN.getCol()].getValue()) this.results.setHigest(this.board[posN.getFil()][posN.getCol()].getValue());//Actualizamos highest
							alreadyMerge = posN.getCol();//ponemos la posicion fusionada en el array 
							posN = null;//valor null para no seguir fusionandose
							cellMoved++;
								
						}//Si el vecino esta vacio movemos
						if( posN != null &&  this.board[posN.getFil()][posN.getCol()].isEmpty()) {
							
							setCell(posN,this.board[pos.getFil()][pos.getCol()].getValue());//movimiento de celula
							setCell(pos,0);
							//Movemos los objetos pos y posN para que se mueva la celula hasta que se fusione o no pueda moverse mas
							pos = posN;
							posN = pos.neighbour(dir,this.getBoardSize());
							cellMoved++;
							
						}
						//Revisa que ninguna casila que no pueda fusionarse o moverse se quede en tierra de nadie
						else posN = null;
					}	
				}
			}
			//Si el numero de celulas movidas o fusiones es distinto de cero es que se ha realizado un movimiento con exito
			if(cellMoved !=0)this.results.setMovement(true);
		}
		return this.results;//Devulve un bojeto de tipo MoveResults
	}
	 
	
	
	//Comprueba que las celulas del tablero esten vacias(true), si hay alguna que no lo esta (false)
	public boolean fullBoard(){
		for(int i= 0; i < this.getBoardSize() ; i++) {
			for(int j = 0; j < this.getBoardSize(); j++) {
				if(this.board[i][j].isEmpty()) return false;
				
			}
		}
		return true;
	}
	
	
	
	//Comprueba que se puede realizar alguna fusion
	public boolean noMerge(){
			
		for(int i =0; i < (this.getBoardSize() - 1); i++) {// comprobamos que no se puede fusionar ninguna posicion
			for(int j =0; j < (this.getBoardSize() - 1);j++){
				if(this.board[i][j].canMerge(this.board[i+1][j]) || 
						this.board[i][j].canMerge(this.board[i][j + 1]))return false;
			}
		}
		
		for (int f = 0; f < (this.getBoardSize() -1);f++) {// comprobamos la ultima fila
			if(this.board[f][this.getBoardSize() - 1].canMerge(this.board[f +1][this.getBoardSize() -1 ])) return false;
		}
		
		for(int c = 0; c < (this.getBoardSize() -1);c++) {// comprobamos la ultima columna
			if(this.board[this.getBoardSize() - 1][c].canMerge(this.board[this.getBoardSize() - 1][ c + 1]))return false;
		}
		return true;
	}

	
	
	//Comprueba si has ganado la partida
	public boolean win() {
		boolean win = false;
		for(int i=0;i < this.getBoardSize();i++) {
			for(int j=0; j < this.getBoardSize();j++) {
				if(this.board[i][j].getValue()== 2048)win = true;
				
			}
		}
		return win;
	}
	
	
	
	//Devuleve un entero con la cantidad de celulas vacias
	public int howManyFree(){
		int howManyfree = 0;
		for(int i =0; i < this.getBoardSize(); i++){
			for( int j =0; j < this.getBoardSize() ; j++){
				if(this.board[i][j].isEmpty()) ++howManyfree;
			}
		}
		return howManyfree;
	}
	
	
	
	//Devuelve un array de objetos Position que indican las celulas vacias del tablero
	public Position[] freeCells(int l){
		Position[] aux = new Position[l];
		int k =0;//contador
		for(int i =0; i < this.getBoardSize(); i++){
			for( int j =0; j < this.getBoardSize() ; j++){
				if(this.board[i][j].isEmpty()){
					aux[k] = new Position(i,j);
					k++;
				}
			}
		}
		return aux;//Devuelve array de objetos Position
	}
	
	

	//Pinta el tablero
	public void print(){
		String board;
		
		String hDelimiter = "_";
		
		for(int i =0;i < this.getBoardSize();i++){
			board = " " + MyStringUtils.repeat(hDelimiter, 8 * this.getBoardSize() -1) + " \n";
			board += "|";
			for (int j =0; j < this.getBoardSize(); j++){
				board += MyStringUtils.centre(Integer.toString(this.board[i][j].getValue()), 7) + "|";
			}
			board+="\n";
			System.out.print(board);
			

		}
		board = " " + MyStringUtils.repeat(hDelimiter, 8 * this.getBoardSize() -1) + " ";
		System.out.print(board);
	}


}
