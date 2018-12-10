package tp.p2.control.commands;

import java.util.Scanner;
import tp.p2.control.exceptions.*;
import tp.p2.logic.multigames.Game;


public class RedoCommand extends NoParamsCommand{

	//CONSTRUCTORA
	public RedoCommand() {
		super("redo", "Rehacer movimiento deshecho.");
		
	}
	
	//EXECUTE
	public boolean execute(Game game) throws CommandExecuteException{
		try {
				game.redo();
				return true;
		}
		catch(EmptyStackException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException();
		}
	}
	
	//PARSEADOR
	public Command parse(String[] commandWords, Scanner sc) {
		if(commandWords.length == 1 && commandWords[0].equalsIgnoreCase("redo")) return this;
		else return null;
	}
}
