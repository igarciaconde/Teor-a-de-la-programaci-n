package tp.p2.control.commands;

import tp.p2.logic.multigames.Game;

import tp.pr2.control.Controller;
import tp.p2.logic.multigames.*;


import java.util.Scanner;
import tp.p2.control.exceptions.*;
public class PlayCommand extends Command {
	
	private GameType tipo;//ATRIBUTO DE TIPO GAMETYPE
	private int initCells;
	private int boardSize;
	private long rand;
	
	//CONSTRUCTORA : ASIGNA UN NOMBRE DEPENDIENDO DEL TIPO DE JUEGO
	public PlayCommand(String tipo) {
		super("play", "Jugar un juego nuevo entre los tipos : original, fib, inverse.");
		switch(tipo){
		case("play original"): 
			this.tipo = GameType.ORIG;
			this.tipo.setNombre("original");
		case("play fib"):
			this.tipo= GameType.FIB;
			this.tipo.setNombre("fib");
		case("play inverse"): 
			this.tipo = GameType.INV;
			this.tipo.setNombre("inverse");
		}
		this.boardSize=4;
		this.initCells=2;
		this.rand=100;
	}

	
	//EXECUTE: PEDIMOS LOS NUEVOS PARAMETROS DE NUESTRO JUEGO Y LO MODIFICAMOS LLAMANDO A LAS REGLAS DEPENDIENDO DEL TIPO DE JUEGO
	public boolean execute(Game game) {
		game.newGame(this.boardSize,this.initCells,this.rand,this.tipo.getGameRules(tipo.getNombre()));
		return true;
	}
	

	//PARSER: PARSEAMOS Y ESTABLECEMOS EL ATRIBUTO TIPO, DEPENDIENDO DE CUAL SEA LE ASIGNAREMOS UN NOMBRE
	public Command parse(String[] commandWords, Scanner in)  throws CommandParseException {
		if(commandWords.length==1 && commandWords[0].equals("play")) throw new CommandParseException("Falta el tipo de juego.");
		else if(commandWords.length == 2 && commandWords[0].equals("play")){
			switch(commandWords[1]){
			case("original"): 
				this.tipo = GameType.ORIG;
				this.tipo.setNombre("original");
				break;
			case("fib"):
				this.tipo= GameType.FIB;
				this.tipo.setNombre("fib");
				break;
			case("inverse"): 
				this.tipo = GameType.INV;
				this.tipo.setNombre("inverse");
				break;
			default: throw new CommandParseException(commandWords[1] + " no es un tipo de juego.");
			}
			
			String[] words;
			System.out.println("¿Que tamaño de tablero quiere?");
			do {
				words = in.nextLine().toLowerCase().trim().split(" s+");
				if(!words[0].equalsIgnoreCase("")){
					this.boardSize = Integer.parseInt(words[0]);
				}
				else System.out.println("Por defecto.");
				if(this.boardSize < 2)System.out.println("Introduce un tamaño mayor.");
			}while(this.boardSize< 2);
			
			System.out.println("¿Cuantas celdas iniciales quiere?");
			do {
				words = in.nextLine().toLowerCase().trim().split(" s+");
				if(!words[0].equalsIgnoreCase("")){
					this.initCells = Integer.parseInt(words[0]);
				}
				else System.out.println("Por defecto.");
				if((this.boardSize*this.boardSize) < this.initCells) System.out.println("Introduce menos celdas iniciales.");
			}while((this.boardSize*this.boardSize) < this.initCells);
			
			System.out.println("¿Que semilla utilizará?");
			words = in.nextLine().toLowerCase().trim().split(" s+");
			if(!words[0].equalsIgnoreCase("")){
				this.rand = Long.parseLong(words[0]);
			}
			else System.out.println("Por defecto.");
			return this;
			
		}
		else return null;
	}

}
