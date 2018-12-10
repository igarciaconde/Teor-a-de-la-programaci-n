package tp.p2.control.commands;

import java.util.Scanner;
import tp.p2.control.exceptions.*;
import tp.p2.logic.multigames.Game;


public class UndoCommand extends NoParamsCommand{
	
	//COSNTRUCTORA
	public UndoCommand() {
		super("undo", "Retroceder un movimiento.");
		
	}
	
	//EXECUTE
	public boolean execute(Game game)throws CommandExecuteException {
		try {
			game.undo();
			return true;
		}
		catch(EmptyStackException e){
			System.out.println(e.getMessage());
			throw new CommandExecuteException();
		}
		
	}
	
	//PARSEADOR
	public Command parse(String[] commandWords, Scanner sc) throws CommandParseException{
		if(commandWords.length == 1 && commandWords[0].equalsIgnoreCase("undo")) return this;
		else return null;
	}

}
