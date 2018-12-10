package tp.p2.control.commands;
import java.util.Scanner;
import tp.p2.logic.multigames.Game;

public class ResetCommand extends NoParamsCommand {
	
	//CONSTRUCTORA
	public ResetCommand() {
		
		super("reset","Resetea el juego.");
	}

	//EXECUTE
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

	//PARSEADOR
	public Command parse(String[] commandWords, Scanner sc) {
		
		if(commandWords.length == 1 && commandWords[0].equalsIgnoreCase("reset")) return this;
		else return null;
	}

}
