header {
	package sticky;
}

class Analex extends Lexer;
	options {
		k=2;
		importVocab=Anasint;
		exportVocab=Analex;
	}
	
	tokens {
		ENTERO= "int";
		REAL= "real";
	
		//Variables
		VAR="var";
		SUP="sup";
		
	    //Loops
	    B_FOR="para"; 
	    B_WHILE="mientras";
	    
	    //Conditionals
	    IF="si";
	    ELSE="sino";
	    SWITCH="opcion";
	    CASE="caso";
	    END_CASE="fincaso";
	    DEFAULT="defecto"; 
 
		//Stickman's movements
		GIRAR="girar";
		FLEXIONAR="flexionar";
		TIEMPO="tiempo";
		AVANZAR="avanzar";
		MOVER="mover";
		ESTABLECE="establece";
		AVANZA="avanza";
		CABEZA="CABEZA";
		BRAZO="BRAZO";
		PIERNA="PIERNA";
		STICKMAN="STICKMAN";
		IZQ="IZQ";
		DER="DER";
		
		//Display print
		IMPRIMIR="mostrar";
		
		//Others variables
		PI = "PI";
				
	}

	protected NL: ('\n' |
		("\r\n")=>
			"\r\n" | 
			'\r')
		{newline();};

	BLANCO: (' '|'\t'|NL) {$setType(Token.SKIP);};

	//Comments
	COMENTARIO: (COM_LINEA|COM_BLOQUE) {$setType(Token.SKIP);};
	COM_LINEA: "//" (~('\n'|'\r'))*;
	COM_BLOQUE: "/*" (options {greedy=false;}: ~('\n'|'\r')|NL)* "*/";
	
	//Intruction end
	FIN_INSTRUCCION: ';';
	FIN_INTERPRETE: '$';
	
	PAR_IZQ: '(';
	PAR_DER: ')';
	
	SEPARA: ',';
	VERDADERO : "VERDAD";
	FALSO : "FALSO";
	
	DOBLE_PUNTO: ':'; 
	
	LLAVE_IZQ: '{';
	LLAVE_DER: '}';
	
	//Arithmetic operators
	OP_SUM: '+';
	OP_RES: '-';
	OP_MUL: '*';
	OP_DIV: '/';
	OP_INC: "++";
	OP_DEC: "--";
	OP_MOD: '%';
	OP_POT: '^';
	OP_RAIZ: "raiz";
	
	//Asignments operators
	OP_ASIG: '=';
		
	//Conditionals
	OP_MAYOR: '>';
	OP_MENOR: '<';
	OP_IG: "==";
	OP_DIST: "!=";
	OP_OX : "OX";
	OP_NO : "NO"; 
	OP_Y : 'Y';
	OP_O : 'O';


	//Identifiers
	protected LETRA: 'a'..'z'|'A'..'Z';
	protected DIGITO: '0'..'9';
	IDENT: (LETRA|'_') (LETRA|'_'|DIGITO)*;

	//Numbers
	NUMERO: ( (DIGITO )+ '.' ) => (DIGITO )+ '.' ( DIGITO )* { $setType (REAL); } | ( DIGITO )+ { $setType (ENTERO);};
			
	//Strings
	CADENA: '"' (options {greedy=false;}: ~('\\') | "\\\"")* '"';
	