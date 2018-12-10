package tp.p2.control.commands;
import java.util.Scanner;
import tp.p2.logic.multigames.Game;
import tp.p2.control.exceptions.*;

public class ExitCommand extends NoParamsCommand {

	//CONSTRUCTORA
	public ExitCommand() {
		super("exit", "Salir del programa.");
	}

	//METODO EJECUTAR
	public boolean execute(Game game) throws GameOverException {
		game.setExit();
		throw new GameOverException();
	}
	
	//METODO PARSE
	public Command parse(String[] commandWords, Scanner sc) {
		if(commandWords.length == 1 && commandWords[0].equalsIgnoreCase("exit")) return this;
		else return null;
	}

}
