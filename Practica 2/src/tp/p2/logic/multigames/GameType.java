package tp.p2.logic.multigames;

public enum GameType { FIB , ORIG, INV;//ENUMERADO QUE REPRESENTA LA REGLAS DEL JUEGO

	private String nombre;//STRING QUE LE DA NOMBRE A CADA TIPO DEL ENUMERADO
	private GameRules rules;//ATRIBUTO DE TIPO GAMERULES PARA ASIGNARSELO A UN TIPO DEL ENUMERADO
	
	//DEVUELVE EL NOMBRE DEPENDIENDO DEL TIP DE JUEGO
	public String getNombre(){
		return this.nombre;
	}
	
	//ESTABLECE UN NOBRE DEPENDIENDO DEL TIPO DE JUEGO
	public void setNombre(String s){
		this.nombre = s;
	}
	
	//DEVUELVE UNAS NUEVAS REGLAS DEPENDIENDO DEL TIPO DE JUEGO
	public GameRules getGameRules(String tipo){
		switch(tipo){
		case ("original"):
			this.rules = new Rules2048();
		return this.rules;
		case("fib"): 
			this.rules = new RulesFib();
		return this.rules;
		case("inverse"):
			this.rules= new RulesInverse();
		return this.rules;
		default: return this.rules;
		}
	}
	
	
	
	



}
