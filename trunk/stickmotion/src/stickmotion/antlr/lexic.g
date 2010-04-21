header {
	package stickmotion.antlr;
}

class SMLexer extends Lexer;
	options {
		k=2;
		// importVocab=Anasint;
	}
	
	tokens {
		//Numeros		
		REAL;
		INTEGER;

		//Variables
		VAR="var";
		SUP="sup";	
		
		//Booleanos
		TRUE="V";
		FALSE="F";
	
	    //Bucles
	    B_FOR="para";
	    B_WHILE="mientras";
	    
	    //Estructuras condicionales
	    IF="si";
	    ELSE="sino";
	    SWITCH="opcion";
	    CASE="caso";
	    END_CASE="fincaso";
	    DEFAULT="defecto";    
    		
		//Movimientos Stickman
		ROTATE="girar";
		FLEX="flexionar";
		HEAD="CABEZA";
		ARM="BRAZO";
		LEG="PIERNA";
		STICKMAN="STICKMAN";
		LEFT="IZQ";
		RIGHT="DER";
		
		//Inclusion de codigo
		INCLUDE="incluir";
		
		//Definicion de funciones
		DEF="def";
				
	}

	//Salto de linea
	protected NL: ('\n' |
		("\r\n")=>
			"\r\n" |
			'\r')
		{newline();};

	//Blancos
	BLANK: ' '|'\t'|NL {$setType(Token.SKIP);};

	//Comentarios
	COMMENT: COM_LINE|COM_BLOCK ;
	COM_LINE: "//" (~('\n'|'\r'))* NL;
	COM_BLOCK: "/*" (options {greedy=false;}: ~('\n'|'\r')|NL)* "*/";
	
	//Fin de instruccion
	END_INSTRUCTION: ';';
	
	//Parentesis
	PAR_L: '(';
	PAR_R: ')';
	
	//Llaves
	BRACE_L: '{';
	BRACE_R: '}';
	
	//Operadores aritmeticos
	OP_ADD: '+';
	OP_SUP: '-';
	OP_MUL: '*';
	OP_DIV: '/';
	OP_INC: "++";
	OP_DEC: "--";
	OP_MOD: '%';
	OP_POT: '^';
	OP_SQRT: "raiz";
	
	//Operadores de asignacion
	OP_ASIG: '=';
	
	//Operadores condicionales
	OP_G: '>';
	OP_L: '<';
	OP_GE: OP_G '=';
	OP_LE: OP_L '=';
	OP_E: "==";
	OP_NE: "!=";
	
	//Operadores logicos
	OP_AND: 'Y';
	OP_OR: 'O';
	OP_XOR: "OX";
	OP_NOT: "NO"|'!';
	
	//Identificadores
	protected LETTER: 'a'..'z'|'A'..'Z';
	protected DIGIT: '0'..'9';
	IDENT: (LETTER|'_') (LETTER|'_'|DIGIT)*;

	//Numeros
	NUMBER:
		((DIGIT)+ '.')=>
			(DIGIT)+ ('.' (DIGIT)+)? {$setType(REAL);} |
			(DIGIT)+ {$setType(INTEGER);}
		;
		
	//Cadenas
	STRING: '"' (options {greedy=false;}: ~('\\') | "\\\"")* '"';    
    
    
