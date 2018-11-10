package tp.pr1;

import java.util.Scanner;

public class Controller {

	private Game game;
	private Scanner in;
	
	
	public Controller(int size,int initCells,long seed){//constructora
		this.game = new Game(size,initCells,seed);
		this.in = new Scanner(System.in);
	}
	
	//El método principal, hace que el usuario juegue
	void run() {
		String variableString;
		
		
		this.game.printG();//pinta el juego
		variableString = "";// string que recoge lo introducido por pantalla
		System.out.print("Type 'help' for help\n");
		while(!variableString.equals("exit")){// mientras que no sea igual a exit
			
			
			variableString = this.in.nextLine();
			//El array String[] es la instrucción decodificada
			String[] arInst = variableString.trim().toLowerCase().split(" +");
			
			System.out.print("Command >" + variableString.trim().toLowerCase() + "\n");
			
			//Posibles instrucciones: 'help', 'reset' o 'exit'
			if(arInst.length == 1) {
				switch(arInst[0]) {
				case "help":
					System.out.print("Teclea 'move right/up/left/down' para mover las celdas y fusionarlas \n");
					System.out.print("Teclea 'reset' para resetear la partida\n");
					System.out.print("Teclea 'exit' para salir del juego\n");
					break;
				case "reset":
					this.game.reset();
					this.game.printG();
					break;
				case "exit":
					System.out.print("You lose");
					break;
				//Caso Contrario	
				default: System.out.print("Instruccion no reconocida\n");
				}
			}
			
			//Instrucción 'move'
			else if(arInst.length==2) {
				if(arInst[0].equals("move")) {
					switch(arInst[1]){// dependiendo de la direccion se ejecutara un codigo u otro
					case "right": 
						this.game.move(Direction.RIGHT);
						this.game.printG();
						if(this.game.winG()) {
							variableString = "exit";
							System.out.print("You win");
						}
						else if(this.game.gameO()) {
							variableString = "exit";
							System.out.print("You lose");
						}
						break;
					case "left": 
						this.game.move(Direction.LEFT);
						this.game.printG();
						if(this.game.winG()) {
							variableString = "exit";
							System.out.print("You win");
						}
						else if(this.game.gameO()) {
							variableString = "exit";
							System.out.print("You lose");
						}
						break;
					case "down":
						this.game.move(Direction.DOWN);
						this.game.printG();
						if(this.game.winG()) {
							variableString = "exit";
							System.out.print("You win");
						}
						else if(this.game.gameO()) {
							variableString = "exit";
							System.out.print("You lose");
						}
						break;
					case "up" :
						this.game.move(Direction.UP);
						this.game.printG();
						if(this.game.winG()) {
							variableString = "exit";
							System.out.print("You win");
						}
						else if(this.game.gameO()) {
							variableString = "exit";
							System.out.print("You lose");
						}
						break;
					//'move' + 'dirección no válida'
					default:
						System.out.print("Instruccion no reconocida\n");
						break;
					}
				}
				//Instrucción de 2 palabras sin 'move'
				else System.out.print("Instruccion no reconocida\n");
			}
			//Instrucción de 3 palabras o más
			else System.out.print("Instruccion no reconocida\n");
			}
		
	}
}
