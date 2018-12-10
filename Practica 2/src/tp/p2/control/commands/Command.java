package tp.p2.control.commands;

import java.util.Scanner;
import tp.p2.control.exceptions.*;
import tp.p2.logic.multigames.Game;

public abstract class Command {
	
	//CLASE ABSTRACTA COMMAND, SUPERCALSE DE TODOS LOS COMANDOS
	private String helpText;//ATRIBUTO QUE CONSTIENE EL TEXTO DE AYUDA
	private String commandText;//ATRIBUTO QUE CONSTIENE EL TEXTO DEL COMANDO
	protected final String commandName;//COMANDO YA TRATADO
	
	
	//CONSTRUCTORA
	public Command(String commandInfo, String helpInfo){
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}
	
	//METODO NO DEFINIDO
	public abstract boolean execute(Game game) throws CommandExecuteException, GameOverException;
	
	//METODO NO DEFINIDO
	public abstract Command parse(String[] commandWords, Scanner sc)throws CommandParseException;
	
	//METODO YA DEFINIDO, DEVUELVE EL TEXTO DE AYUDA
	public String helpText(){
		return " " + commandText + ": " + helpText;
		}
}
	