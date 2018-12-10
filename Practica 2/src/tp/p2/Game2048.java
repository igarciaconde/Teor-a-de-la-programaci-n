package tp.p2;
import java.util.Random;

import tp.p2.control.exceptions.CommandExecuteException;
import tp.p2.control.exceptions.CommandParseException;
import tp.p2.control.exceptions.EmptyStackException;
import tp.p2.control.exceptions.GameOverException;
import tp.p2.logic.multigames.*;
import tp.pr2.control.Controller;

public class Game2048 {

	public static void main(String[] args) throws CommandParseException, CommandExecuteException, GameOverException, EmptyStackException {
		// TODO Auto-generated method stub
			
		int size; // Tamaño del tablero
		int initCells; // Celdas iniciales
		long seed; // Semilla
		Controller controller;//Objeto controller
		GameRules gr = new Rules2048();
		
		if(args.length == 3){// Si se obtienen 3 argumentos en el game...
			seed = Long.parseLong(args[2]);
		}
		else seed = new Random().nextInt(1000);// Si se pasan solo 2 argumentos
		
		size = Integer.parseInt(args[0]);
		initCells = Integer.parseInt(args[1]);
		
		
		if (size < 2) System.out.print("Introduce una dimension mas grande\n");//El tamaño no puede ser menor que 2
		if ((size*size) < initCells)System.out.print("Introduzca menos casillas iniciales\n");//El tamaño tiene que ser mayor a las valdosas iniciales
		else {
		
			controller = new Controller(size,initCells,seed,gr);// Se crea el objeto controller
			controller.run();// Se ejecuta el metodo principal de Controller
		}
		
	}

}
