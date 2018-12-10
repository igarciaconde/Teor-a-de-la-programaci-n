package tp.p2.control.commands;

import tp.p2.logic.multigames.Game;
import java.util.Scanner;
import tp.p2.Direction;
import tp.p2.control.exceptions.*;



public class MoveCommand extends Command{
	
	private Direction dir;//ATRIBUTO DIRECCION
	
	//CONSTRUCTORA
	public MoveCommand(Direction dir) {
		super("move","Mover ficha en alguna direcion...right, left, up and down." );
		this.dir = dir;
	}

	//METODO EXECUTE
	public boolean execute(Game game) throws GameOverException{

		game.move(this.dir);
		if(game.getResults()== true)
			return true;
		else{
			System.out.println("No valid move");
			return false;
		}
	}
	
	

	//PARSEADOR: DEPENDIENDO DE LO QUE SE INTRODUZCA POR PANTALLA SE CAMBIA EL ATRIBUTO DIR
	public Command parse(String[] commandWords, Scanner sc) throws CommandParseException{
		
		if(commandWords.length == 1 && commandWords[0].equals("move")) throw new CommandParseException("falta una direccion.");
		else if(commandWords.length == 2 && commandWords[0].equals("move")){
			switch(commandWords[1]){
			case("up"): 
				this.dir= Direction.UP;
				return this;
			case("down"):
				this.dir= Direction.DOWN;
				return this;
			case("right"): 
				this.dir= Direction.RIGHT;
				return this;
			case("left"): 
				this.dir=Direction.LEFT;
				return this;
			default: throw new CommandParseException(commandWords[1] + " no es una direccion.");
			}
			
		}
		else if (commandWords.length == 3 && commandWords[0].equals("move"))throw new CommandParseException("move no necesita mas de dor argumentos.");
		else return null;
		
	}

}
