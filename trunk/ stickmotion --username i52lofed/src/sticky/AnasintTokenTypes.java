// $ANTLR : "anasint.g" -> "Anasint.java"$

	package sticky;
	import java.util.HashMap;
	import tablasimbolos.TablaSimbolos;
	import java.util.ArrayList;
	import error.Error;

public interface AnasintTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int VAR = 4;
	int IDENT = 5;
	int FIN_INSTRUCCION = 6;
	int OP_ASIG = 7;
	int SEPARA = 8;
	int OP_O = 9;
	int OP_NO = 10;
	int OP_MAYOR = 11;
	int OP_MENOR = 12;
	int OP_IG = 13;
	int OP_DIST = 14;
	int OP_POT = 15;
	int OP_MOD = 16;
	int OP_SUM = 17;
	int OP_RES = 18;
	int OP_MUL = 19;
	int OP_DIV = 20;
	int NUMERO = 21;
	int REAL = 22;
	int VERDADERO = 23;
	int FALSO = 24;
	int PAR_IZQ = 25;
	int PAR_DER = 26;
	int FIN_INTERPRETE = 27;
}
