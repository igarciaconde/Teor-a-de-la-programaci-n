package tp.pr2.control;

import tp.p2.control.commands.Command;
import tp.p2.control.commands.CommandParse;
import tp.p2.control.exceptions.*;
import java.util.Scanner;
import tp.p2.logic.multigames.*;

public class Controller {
	
///////////////////////////////////ATRIBUTOS CLASE CONTROLLER////////////////////////////////////////////////////////
	private Game game;//atributo de tipo game
	private Scanner in;//atributo de tipo scanner que puede leer lo que se introduce por pantalla
	
	
///////////////////////////////////CONSTRUCTORA///////////////////////////////////////////////////////////////////////	
	public Controller(int size,int initCells,long seed, GameRules gr){//constructora
		this.game = new Game(size,initCells,seed,gr);
		this.in = new Scanner(System.in);

	}
	
///////////////////////////////////METODO PRINCIPAL DE CONTROLLER/////////////////////////////////////////////////////
	
	//El método principal, hace que el usuario juegue
	public void run() throws CommandParseException, CommandExecuteException, GameOverException, EmptyStackException {
		
			this.game.printG();
			while(!this.game.gameO()&& !game.getExit() && !this.game.winG()){//mientras no se haya ganado y no se haya salido
				System.out.print("Command > ");
				String[] words = in.nextLine().toLowerCase().trim().split(" +");
				try {
					Command c = CommandParse.parseCommand(words,this.in);//llamamos al comand parser y si devuelve un comado lo ejecuta
					if(c!= null) {//si se devuelve un comando se ejecuta
						if(c.execute(this.game)==true) this.game.printG();
					}
					else throw new CommandParseException();
				}
				catch(CommandExecuteException e) {
					System.out.println(e.getMessage());
				}
				catch(GameOverException i) {
					System.out.println(i.getMessage());
				}
				catch(CommandParseException j) {
					System.out.println(j.getMessage());
				}
				
			}
			if(this.game.winG())System.out.println("YOU WIN");
			
		
	}
}
	
	

