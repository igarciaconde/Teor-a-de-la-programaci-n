package tp.p2.control.commands;
import java.util.Scanner;
import tp.p2.logic.multigames.Game;

public class HelpCommand extends NoParamsCommand {
	
	//CONSTRUCTORA
	public HelpCommand() {
		super("help", "Muestra el texto de ayuda.");
	}

	//METODO EXECUTE
	public boolean execute(Game game) {
		System.out.println(CommandParse.commandHelp() + System.lineSeparator());
		return false;
	}
	
	//METODO PARSE
	public Command parse(String[] commandWords, Scanner sc) {
		if(commandWords.length == 1 && commandWords[0].equalsIgnoreCase("help")) return this;
		else return null;
	}

}
