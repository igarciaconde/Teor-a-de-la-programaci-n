package tp.p2;

public class Position {
	
private int fil;//Atributo entero que representa el numero de una fila
private int col;//Atributo entero que representa el numero de una columna
	
	//Constructora
	public Position (int fil, int col) {
		this.fil = fil;
		this.col = col;
	}
	//Metodo getter del atributo fila
	public int getFil () {
		return this.fil;
	}
	//Metodo getter del atributo columna
	public int getCol () {
		return this.col;
	}
	
	//Devuelve a la posicion vecina si existe, en caso contrario, null
	//Asumimos que la posición this es válida (está dentro de la board)
	public Position neighbour (Direction dir, int size) {
		Position auxPos = null;
		switch (dir) {
		case UP:
			if (this.fil > 0)
				auxPos = new Position(this.fil - 1, this.col);
			break;
		case DOWN:
			if (this.fil < size - 1)
				auxPos = new Position(this.fil + 1, this.col);
			break;
		case RIGHT:
			if (this.col < size - 1)
				auxPos = new Position(this.fil, this.col + 1);
			break;
		case LEFT:
			if (this.col > 0)
				auxPos = new Position(this.fil, this.col - 1);
			break;
		}
		return auxPos;
	}
}
