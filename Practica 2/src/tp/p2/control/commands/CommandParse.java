package tp.p2.control.commands;
import java.util.Scanner;
import tp.p2.Direction;
import tp.p2.control.exceptions.CommandParseException;
public class CommandParse {
	
	//ATRIBUTO QUE ONTIENE TODOS LOS COMANDOS, LOS CREA EN PRIMERA INSTANCIA
	private final static Command[] commands=
		{new HelpCommand(), new MoveCommand(Direction.UP), new ResetCommand(),
		 new ExitCommand(), new UndoCommand(), new RedoCommand(), new PlayCommand("original")};
	
	
	//METODO QUE RECORRE TODOS LOS COMANDOS Y DEVUELVE ALGUNO SI LO HA ENCONTRADO
	public static Command parseCommand(String[] commandWords, Scanner sc) throws CommandParseException{
		if (commandWords.length > 0 || commandWords.length <= 2){
			Command cm;
			for (Command c:commands) {
			cm=c.parse(commandWords,sc);
			if (cm!=null) return cm;
			
			}
		}
		return null;
		
	}
	
	//METODO QUE IMPRIME TODOS LOS HELPTEXT DE TODOS LOS COMANDOS
	public static String commandHelp(){
		String ayuda="";
		for (Command c:commands){
			ayuda+= c.helpText() + System.lineSeparator();
		}
		return ayuda;
		
	}

	
}
