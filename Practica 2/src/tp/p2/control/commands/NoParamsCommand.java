package tp.p2.control.commands;

import java.util.Scanner;
import tp.p2.control.exceptions.*;
import tp.p2.logic.multigames.Game;


public class NoParamsCommand extends Command{

	//ESTA CLASE ES UNA SUBCLASE DE COMMAND Y SUPERCLASE DE OS COMANDOS SIN PARAMETROS
	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		
	}

	
	public boolean execute(Game game)throws CommandExecuteException, GameOverException {
		return false;
	}

	
	public Command parse(String[] commandWords, Scanner sc) throws CommandParseException {
		return null;
	}
	
	
	

}
