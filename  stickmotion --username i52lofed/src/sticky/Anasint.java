// $ANTLR : "anasint.g" -> "Anasint.java"$

	package sticky;
	import java.util.HashMap;
	import tablasimbolos.TablaSimbolos;
	import java.util.ArrayList;
	import error.Error;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class Anasint extends antlr.LLkParser       implements AnasintTokenTypes
 {

		TablaSimbolos tablaSimbolos= new TablaSimbolos();
	
protected Anasint(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public Anasint(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected Anasint(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public Anasint(TokenStream lexer) {
  this(lexer,1);
}

public Anasint(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final void instrucciones() throws RecognitionException, TokenStreamException {
		
		
				Object x=null;
				System.out.println("...INICIANDO STICKY...");		
			
		
		try {      // for error handling
			{
			_loop3185:
			do {
				if ((LA(1)==VAR)) {
					declaracion();
				}
				else {
					break _loop3185;
				}
				
			} while (true);
			}
			{
			_loop3187:
			do {
				if ((LA(1)==IDENT)) {
					asignacion();
				}
				else {
					break _loop3187;
				}
				
			} while (true);
			}
			fin_interprete();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public final void declaracion() throws RecognitionException, TokenStreamException {
		
		Token  i1 = null;
		Token  i2 = null;
		Token  punto2 = null;
		Token  i3 = null;
		Token  i3_alt = null;
		Token  punto3 = null;
		String mensaje;Object x = null; ArrayList lista = new ArrayList();
		
		try {      // for error handling
			{
			boolean synPredMatched3191 = false;
			if (((LA(1)==VAR))) {
				int _m3191 = mark();
				synPredMatched3191 = true;
				inputState.guessing++;
				try {
					{
					match(VAR);
					match(IDENT);
					match(FIN_INSTRUCCION);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched3191 = false;
				}
				rewind(_m3191);
inputState.guessing--;
			}
			if ( synPredMatched3191 ) {
				match(VAR);
				i1 = LT(1);
				match(IDENT);
				match(FIN_INSTRUCCION);
				if ( inputState.guessing==0 ) {
					
							  boolean res=tablaSimbolos.put(i1);
							  if(res)
							  	System.out.println("Variable \""+i1.getText()+"\" ha sido declarada");
							  else 
							  	System.out.println("Variable \""+i1.getText()+"\" no ha sido declarada");
							
				}
			}
			else {
				boolean synPredMatched3193 = false;
				if (((LA(1)==VAR))) {
					int _m3193 = mark();
					synPredMatched3193 = true;
					inputState.guessing++;
					try {
						{
						match(VAR);
						match(IDENT);
						match(OP_ASIG);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched3193 = false;
					}
					rewind(_m3193);
inputState.guessing--;
				}
				if ( synPredMatched3193 ) {
					match(VAR);
					i2 = LT(1);
					match(IDENT);
					match(OP_ASIG);
					{
					x=expr_aritmetica();
					}
					punto2 = LT(1);
					match(FIN_INSTRUCCION);
					if ( inputState.guessing==0 ) {
									
										System.out.println("entra2");	
										boolean res = tablaSimbolos.put(i2,x);	// modifico el valor en la tabla de simbolos
										if(res)
								  			System.out.println("Variable \""+i2.getText()+"\" ha sido declarada con valor "+x);
								  		else 
								  			System.out.println("Variable \""+i2.getText()+"\" no ha sido declarada");
								
									
					}
				}
				else {
					boolean synPredMatched3198 = false;
					if (((LA(1)==VAR))) {
						int _m3198 = mark();
						synPredMatched3198 = true;
						inputState.guessing++;
						try {
							{
							match(VAR);
							match(IDENT);
							{
							_loop3197:
							do {
								if ((LA(1)==SEPARA)) {
									match(SEPARA);
									match(IDENT);
								}
								else {
									break _loop3197;
								}
								
							} while (true);
							}
							}
						}
						catch (RecognitionException pe) {
							synPredMatched3198 = false;
						}
						rewind(_m3198);
inputState.guessing--;
					}
					if ( synPredMatched3198 ) {
						match(VAR);
						i3 = LT(1);
						match(IDENT);
						{
						_loop3200:
						do {
							if ((LA(1)==SEPARA)) {
								match(SEPARA);
								i3_alt = LT(1);
								match(IDENT);
								if ( inputState.guessing==0 ) {
									lista.add(i3_alt);
								}
							}
							else {
								break _loop3200;
							}
							
						} while (true);
						}
						punto3 = LT(1);
						match(FIN_INSTRUCCION);
						if ( inputState.guessing==0 ) {
							
													// Tenemos que insertar cada identificador encontrado en la tabla de simbolos
													boolean res = tablaSimbolos.put(i3);
													if(res)
									  					System.out.println("Variable \""+i3.getText()+"\" ha sido declarada");
									  				else 
									  					System.out.println("Variable \""+i3.getText()+"\" no ha sido declarada");
													Token tok;
													for(int j=0; j < lista.size(); j++)
													{
															tok = (Token)lista.get(j); // obtengo un identificador de la lista
															res = tablaSimbolos.put(tok);
															if(res)
									  							System.out.println("Variable \""+tok.getText()+"\" ha sido declarada");
									  						else 
									  							System.out.println("Variable \""+tok.getText()+"\" no ha sido declarada");
													}
												
						}
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}}
					}
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						reportError(ex);
						recover(ex,_tokenSet_1);
					} else {
					  throw ex;
					}
				}
			}
			
	public final void asignacion() throws RecognitionException, TokenStreamException {
		
		Token  i = null;
		Token  punto = null;
		String mensaje = new String(); Object respuesta; Object respuesta2;
		
		try {      // for error handling
			i = LT(1);
			match(IDENT);
			match(OP_ASIG);
			{
			respuesta=expr_aritmetica();
			}
			punto = LT(1);
			match(FIN_INSTRUCCION);
			if ( inputState.guessing==0 ) {
				
							System.out.println("asignacion: "+respuesta);
							//if(ejecutar)
							tablaSimbolos.set(i,respuesta);	
				
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
	}
	
	public final void fin_interprete() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				
						System.out.println("...FINALIZANDO STICKY...");	
					
			}
			match(FIN_INTERPRETE);
			if ( inputState.guessing==0 ) {
				
						consumeUntil(Token.EOF_TYPE);
						consume();
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public final Object  expr_aritmetica() throws RecognitionException, TokenStreamException {
		Object resultado = null;
		
		Token  linea1 = null;
		Object e1; Object e2;
		
		try {      // for error handling
			e1=expr_mod();
			if ( inputState.guessing==0 ) {
				
						resultado = e1;	
					
			}
			{
			_loop3216:
			do {
				if ((LA(1)==OP_POT)) {
					linea1 = LT(1);
					match(OP_POT);
					e2=expr_mod();
					if ( inputState.guessing==0 ) {
						
								if(e1 instanceof Integer && e2 instanceof Integer)
								{
									int valor1 = new Integer(e1.toString()).intValue();
									int valor2 = new Integer(e2.toString()).intValue();
									
									resultado = new Double(Math.pow(valor1, valor2));
								}
								else
								{
									if(e1 instanceof String || e2 instanceof String)
									{
										Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
									}
									else
									{
									
										if(e1 instanceof String || e2 instanceof String)
											Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
										else
										{
											double valor1 = new Double(e1.toString()).doubleValue();
											int valor2 = new Integer(e2.toString()).intValue();
											resultado = new Double(Math.pow(valor1, valor2));
										}
									}
								}
							
							
					}
				}
				else {
					break _loop3216;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		return resultado;
	}
	
	public final Object  expr_booleana() throws RecognitionException, TokenStreamException {
		Object respuesta=null;
		
		Token  linea = null;
		Object exp; Object exp2;
		
		try {      // for error handling
			exp=expresionOR();
			if ( inputState.guessing==0 ) {
				respuesta = exp;
			}
			{
			_loop3207:
			do {
				if ((LA(1)==OP_O)) {
					linea = LT(1);
					match(OP_O);
					exp2=expresionOR();
					if ( inputState.guessing==0 ) {
						
									if(exp2 != null && respuesta!=null) //3
									{
										if(exp2 instanceof Boolean && respuesta instanceof Boolean)
										{
											boolean var1 = new Boolean(respuesta.toString()).booleanValue();
											boolean var2 = new Boolean(exp2.toString()).booleanValue();
											var1 = var1 || var2;
											respuesta = new Boolean(var1);	
										}
										else
										{
												Error.errorExpresion(1,linea.getLine());
										}	
									}
								
					}
				}
				else {
					break _loop3207;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		return respuesta;
	}
	
	public final Object  expresionOR() throws RecognitionException, TokenStreamException {
		Object respuesta=null;
		
		Token  linea = null;
		Object exp; Object exp2;
		
		try {      // for error handling
			exp=expresionAND();
			if ( inputState.guessing==0 ) {
				respuesta = exp;
			}
			{
			_loop3210:
			do {
				if ((LA(1)==OP_O)) {
					linea = LT(1);
					match(OP_O);
					exp2=expresionAND();
					if ( inputState.guessing==0 ) {
						
									if(exp2 != null && respuesta!=null) //3
									{
										if(exp2 instanceof Boolean && respuesta instanceof Boolean)
										{
											boolean var1 = new Boolean(respuesta.toString()).booleanValue();
											boolean var2 = new Boolean(exp2.toString()).booleanValue();
											var1 = var1 && var2;
											respuesta = new Boolean(var1);	
										}
										else
										{
												Error.errorExpresion(1,linea.getLine());
										}	
									}
								
					}
				}
				else {
					break _loop3210;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return respuesta;
	}
	
	public final Object  expresionAND() throws RecognitionException, TokenStreamException {
		Object respuesta=null;
		
		Token  linea = null;
		Object exp; Object exp2;
		
		try {      // for error handling
			switch ( LA(1)) {
			case OP_NO:
			{
				linea = LT(1);
				match(OP_NO);
				exp2=expr_relacional();
				if ( inputState.guessing==0 ) {
					
								if(exp2 != null) 				// 2)
								{
									if(exp2 instanceof Boolean)	
									{
										boolean var1 = new Boolean(exp2.toString()).booleanValue();
										respuesta = new Boolean(!var1);	
									}
									else
									{
											Error.errorExpresion(1,linea.getLine());
									}	
								}
							
				}
				break;
			}
			case IDENT:
			case NUMERO:
			case REAL:
			case VERDADERO:
			case FALSO:
			case PAR_IZQ:
			{
				exp=expr_relacional();
				if ( inputState.guessing==0 ) {
					respuesta=exp;
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return respuesta;
	}
	
	public final Object  expr_relacional() throws RecognitionException, TokenStreamException {
		Object respuesta = null;
		
		Token  linea = null;
		Token  linea2 = null;
		Token  linea3 = null;
		Token  linea4 = null;
		Object e1; Object e2;
		
		try {      // for error handling
			e1=expr_aritmetica();
			{
			switch ( LA(1)) {
			case OP_MAYOR:
			{
				linea = LT(1);
				match(OP_MAYOR);
				e2=expr_aritmetica();
				if ( inputState.guessing==0 ) {
					
							if(e1 instanceof Integer && e2 instanceof Integer)
							{
								int num1 = new Integer(e1.toString()).intValue();
								int num2 = new Integer(e2.toString()).intValue();
								respuesta = new Boolean(num1>num2);
							}
							
							if(e1 instanceof Integer && e2 instanceof Double)
							{
								int num1 = new Integer(e1.toString()).intValue();
								double num2 = new Double(e2.toString()).doubleValue();
								respuesta = new Boolean(num1>num2);
							}
							
							if(e1 instanceof Double && e2 instanceof Double)
							{
								double num1 = new Double(e1.toString()).doubleValue();
								int num2 = new Integer(e2.toString()).intValue();
								respuesta = new Boolean(num1>num2);
							}
							
							if(e1 instanceof Double && e2 instanceof Double)
							{
								double num1 = new Double(e1.toString()).doubleValue();
								double num2 = new Double(e2.toString()).doubleValue();
								respuesta = new Boolean(num1>num2);
							}
							
							if(((e1 instanceof String == true) && (e2 instanceof String == false))
								||((e1 instanceof String == false)&&(e2 instanceof String == true)))
							{
								Error.visualizarError(1,linea.getLine(), "No se puede realizar expresiones relacionales con datos de distinto tipo");
								respuesta = new Boolean(false);
							}
							
							
							
								
						
				}
				break;
			}
			case OP_MENOR:
			{
				linea2 = LT(1);
				match(OP_MENOR);
				e2=expr_aritmetica();
				if ( inputState.guessing==0 ) {
					
							if(e1 instanceof Integer && e2 instanceof Integer)
							{
								int num1 = new Integer(e1.toString()).intValue();
								int num2 = new Integer(e2.toString()).intValue();
								respuesta = new Boolean(num1<num2);
							}
							
							if(e1 instanceof Integer && e2 instanceof Double)
							{
								int num1 = new Integer(e1.toString()).intValue();
								double num2 = new Double(e2.toString()).doubleValue();
								respuesta = new Boolean(num1<num2);
							}
							
							if(e1 instanceof Double && e2 instanceof Double)
							{
								double num1 = new Double(e1.toString()).doubleValue();
								int num2 = new Integer(e2.toString()).intValue();
								respuesta = new Boolean(num1<num2);
							}
							
							if(e1 instanceof Double && e2 instanceof Double)
							{
								double num1 = new Double(e1.toString()).doubleValue();
								double num2 = new Double(e2.toString()).doubleValue();
								respuesta = new Boolean(num1<num2);
							}
							
							if(((e1 instanceof String == true) && (e2 instanceof String == false))
								||((e1 instanceof String == false)&&(e2 instanceof String == true)))
							{
								Error.visualizarError(1,linea2.getLine(), "No se puede realizar expresiones relacionales con datos de distinto tipo");
								respuesta = new Boolean(false);
							}
						
				}
				break;
			}
			case OP_IG:
			{
				linea3 = LT(1);
				match(OP_IG);
				e2=expr_aritmetica();
				if ( inputState.guessing==0 ) {
					
							if(e1 instanceof Integer && e2 instanceof Integer)
							{
								int num1 = new Integer(e1.toString()).intValue();
								int num2 = new Integer(e2.toString()).intValue();
								respuesta = new Boolean(num1==num2);
							}
							
							if(e1 instanceof Integer && e2 instanceof Double)
							{
								int num1 = new Integer(e1.toString()).intValue();
								double num2 = new Double(e2.toString()).doubleValue();
								respuesta = new Boolean(num1==num2);
							}
							
							if(e1 instanceof Double && e2 instanceof Double)
							{
								double num1 = new Double(e1.toString()).doubleValue();
								int num2 = new Integer(e2.toString()).intValue();
								respuesta = new Boolean(num1==num2);
							}
							
							if(e1 instanceof Double && e2 instanceof Double)
							{
								double num1 = new Double(e1.toString()).doubleValue();
								double num2 = new Double(e2.toString()).doubleValue();
								respuesta = new Boolean(num1==num2);
							}
							
							if(((e1 instanceof String == true) && (e2 instanceof String == false))
								||((e1 instanceof String == false)&&(e2 instanceof String == true)))
							{
								Error.visualizarError(1,linea3.getLine(), "No se puede realizar expresiones relacionales con datos de distinto tipo");
								respuesta = new Boolean(false);
							}
						
				}
				break;
			}
			case OP_DIST:
			{
				linea4 = LT(1);
				match(OP_DIST);
				e2=expr_aritmetica();
				if ( inputState.guessing==0 ) {
					
							if(e1 instanceof Integer && e2 instanceof Integer)
							{
								int num1 = new Integer(e1.toString()).intValue();
								int num2 = new Integer(e2.toString()).intValue();
								respuesta = new Boolean(num1!=num2);
							}
							
							if(e1 instanceof Integer && e2 instanceof Double)
							{
								int num1 = new Integer(e1.toString()).intValue();
								double num2 = new Double(e2.toString()).doubleValue();
								respuesta = new Boolean(num1!=num2);
							}
							
							if(e1 instanceof Double && e2 instanceof Double)
							{
								double num1 = new Double(e1.toString()).doubleValue();
								int num2 = new Integer(e2.toString()).intValue();
								respuesta = new Boolean(num1!=num2);
							}
							
							if(e1 instanceof Double && e2 instanceof Double)
							{
								double num1 = new Double(e1.toString()).doubleValue();
								double num2 = new Double(e2.toString()).doubleValue();
								respuesta = new Boolean(num1!=num2);
							}
							
							if(((e1 instanceof String == true) && (e2 instanceof String == false))
								||((e1 instanceof String == false)&&(e2 instanceof String == true)))
							{
								Error.visualizarError(1,linea4.getLine(), "No se puede realizar expresiones relacionales con datos de distinto tipo");
							}
						
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return respuesta;
	}
	
	public final Object  expr_mod() throws RecognitionException, TokenStreamException {
		Object resultado = null;
		
		Token  linea1 = null;
		Object e1; Object e2;
		
		try {      // for error handling
			e1=expr();
			if ( inputState.guessing==0 ) {
				
						resultado = e1;
					
			}
			{
			_loop3219:
			do {
				if ((LA(1)==OP_MOD)) {
					linea1 = LT(1);
					match(OP_MOD);
					e2=expr();
					if ( inputState.guessing==0 ) {
						
								if(e1 instanceof Integer && e2 instanceof Integer)
								{
									int valor1 = new Integer(e1.toString()).intValue();
									int valor2 = new Integer(e2.toString()).intValue();
									
									resultado = new Integer(valor1%valor2);
								}
								
								if(e1 instanceof Integer && e2 instanceof Double)
								{
									int valor1 = new Integer(e1.toString()).intValue();
									double valor2 = new Double(e2.toString()).doubleValue();
									
									resultado = new Double(valor1%valor2);
								}
								
								if(e1 instanceof Double && e2 instanceof Integer)
								{
									double valor1 = new Double(e1.toString()).doubleValue();
									int valor2 = new Integer(e2.toString()).intValue();
									
									resultado = new Double(valor1%valor2);
								}
								
								if(e1 instanceof Double && e2 instanceof Double)
								{
									double valor1 = new Double(e1.toString()).doubleValue();
									double valor2 = new Double(e2.toString()).doubleValue();
									
									resultado = new Double(valor1%valor2);
								}
								
								if(e1 instanceof String || e2 instanceof String)
								{
									Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
								}
								
								if(e1 instanceof Boolean || e2 instanceof Boolean)
								{
									Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
								}
								
							
					}
				}
				else {
					break _loop3219;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		return resultado;
	}
	
	public final Object  expr() throws RecognitionException, TokenStreamException {
		Object resultado = null;
		
		Token  linea1 = null;
		Token  linea2 = null;
		Object e1; Object e2; Object e3; Object e4;
		
		try {      // for error handling
			e1=expr_mult();
			if ( inputState.guessing==0 ) {
				
						resultado = e1;
					
			}
			{
			_loop3222:
			do {
				switch ( LA(1)) {
				case OP_SUM:
				{
					linea1 = LT(1);
					match(OP_SUM);
					e2=expr_mult();
					if ( inputState.guessing==0 ) {
						
								if(e1 instanceof Integer && e2 instanceof Integer)
							  	{
							  		int valor1 = new Integer(e1.toString()).intValue();
							  		int valor2 = new Integer(e2.toString()).intValue();
							  		
							  		resultado = new Integer(valor1+valor2);
							  		
							  		e1=resultado;
							  	}
							  	else
							  	{
							  		if(e1 instanceof String || e2 instanceof String)
							  		{
							  			if(e1 instanceof String && e2 instanceof String)
							  			{
							  				String valor1 = new String(e1.toString());
							  				String valor2 = new String(e2.toString());
							  				resultado = new String(valor1+valor2);
							  			}
							  			else
							  			{
							  				Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
							  			}
							  		}
							  		else
							  		{
							  			if(e1 instanceof Boolean || e2 instanceof Boolean)
							  				Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
							  			else
							  			{
							  				double valor1 = new Double(e1.toString()).doubleValue();
							  				double valor2 = new Double(e2.toString()).doubleValue();
							  				resultado = new Double(valor1+valor2);
							  				
							  				e1=resultado;
							  			}
							  		}
							  	}	
							
					}
					break;
				}
				case OP_RES:
				{
					linea2 = LT(1);
					match(OP_RES);
					e3=expr_mult();
					if ( inputState.guessing==0 ) {
						
								if(e1 instanceof Integer && e3 instanceof Integer)
							  	{
							  		int valor1 = new Integer(e1.toString()).intValue();
							  		int valor2 = new Integer(e3.toString()).intValue();
							  		
							  		resultado = new Integer(valor1-valor2);
							  		
							  		e1=resultado;
							  	}
							  	else
							  	{
							  		if(e1 instanceof String || e3 instanceof String)
							  		{
							  			Error.visualizarError(1,linea2.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
							  		}
							  		else
							  		{
							  			if(e1 instanceof Boolean || e3 instanceof Boolean)
							  				Error.visualizarError(1,linea2.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
							  			else
							  			{
							  				double valor1 = new Double(e1.toString()).doubleValue();
							  				double valor2 = new Double(e3.toString()).doubleValue();
							  				resultado = new Double(valor1-valor2);
							  				
							  				e1=resultado;
							  			}
							  		}
							  	}
							
					}
					break;
				}
				default:
				{
					break _loop3222;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		return resultado;
	}
	
	public final Object  expr_mult() throws RecognitionException, TokenStreamException {
		Object resultado = null;
		
		Token  linea1 = null;
		Token  linea2 = null;
		Object e1; Object e2; Object e3;
		
		try {      // for error handling
			e1=expr_base();
			if ( inputState.guessing==0 ) {
				resultado = e1;
			}
			{
			_loop3225:
			do {
				switch ( LA(1)) {
				case OP_MUL:
				{
					linea1 = LT(1);
					match(OP_MUL);
					e2=expr_base();
					if ( inputState.guessing==0 ) {
						
							  	if(e1 instanceof Integer && e2 instanceof Integer)
							  	{
							  		int valor1 = new Integer(e1.toString()).intValue();
							  		int valor2 = new Integer(e2.toString()).intValue();
							  		
							  		resultado = new Integer(valor1*valor2);
							  		
							  		e1=resultado;
							  	}
							  	
							  	if(e1 instanceof Integer && e2 instanceof Double)
							  	{
							  		int valor1 = new Integer(e1.toString()).intValue();
							  		double valor2 = new Double(e2.toString()).doubleValue();
							  		
							  		resultado = new Double(valor1*valor2);
							  		
							  		e1=resultado;
							  	}
							  	
							  	if(e1 instanceof Double && e2 instanceof Integer)
							  	{
							  		double valor1 = new Double(e1.toString()).doubleValue();
							  		int valor2 = new Integer(e2.toString()).intValue();
							  		
							  		resultado = new Double(valor1*valor2);
							  		
							  		e1=resultado;
							  	}
							  	
							  	if(e1 instanceof Double && e2 instanceof Double)
							  	{
							  		double valor1 = new Double(e1.toString()).doubleValue();
							  		double valor2 = new Double(e2.toString()).doubleValue();
							  		
							  		resultado = new Double(valor1*valor2);
							  		
							  		e1=resultado;
							  	}
							  	
							  	if(e1 instanceof String || e2 instanceof String)
							  	{
							  			Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
							  	}
							  	
							  	if(e1 instanceof Boolean || e2 instanceof Boolean)
							  	{
							  			Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
							  	}
							
					}
					break;
				}
				case OP_DIV:
				{
					linea2 = LT(1);
					match(OP_DIV);
					e3=expr_base();
					if ( inputState.guessing==0 ) {
						
							  	if(e1 instanceof Integer && e3 instanceof Integer)
							  	{
							  		int valor1 = new Integer(e1.toString()).intValue();
							  		int valor2 = new Integer(e3.toString()).intValue();
							  		
							  		if((valor2 !=0) && (valor1 != 0))
							  		{
							  				resultado = new Integer(valor1/valor2);
							  				e1=resultado;
							  		}
							  		else
							  			Error.visualizarError(1,linea2.getLine(),"ERROR: por 0");
							  	}
							  	else
							  	{
							  		if(e1 instanceof String || e3 instanceof String)
							  		{
							  			Error.visualizarError(1,linea2.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
							  		}
							  		else
							  		{
							  			if(e1 instanceof Boolean || e3 instanceof Boolean)
							  				Error.visualizarError(1,linea2.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
							  			else
							  			{	
							  				double valor1 = new Double(e1.toString()).doubleValue();
							  				double valor2 = new Double(e3.toString()).doubleValue();
							  			
							  				if((valor2 !=0)&&(valor1 != 0))
							  				{	
							  					resultado = new Double(valor1/valor2);
							  					e1=resultado;
							  				}
							  				else
							  					Error.visualizarError(1,linea2.getLine(),"ERROR: por 0");
							  			}
							  		}
							  			
							  	}	
							
					}
					break;
				}
				default:
				{
					break _loop3225;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_7);
			} else {
			  throw ex;
			}
		}
		return resultado;
	}
	
	public final Object  expr_base() throws RecognitionException, TokenStreamException {
		Object resultado = null;
		
		Token  n1 = null;
		Token  n2 = null;
		Token  n3 = null;
		Token  n4 = null;
		Token  id = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case NUMERO:
			{
				n1 = LT(1);
				match(NUMERO);
				if ( inputState.guessing==0 ) {
					resultado = new Integer(n1.getText());
				}
				break;
			}
			case REAL:
			{
				n2 = LT(1);
				match(REAL);
				if ( inputState.guessing==0 ) {
					resultado = new Double(n2.getText());
				}
				break;
			}
			case VERDADERO:
			{
				n3 = LT(1);
				match(VERDADERO);
				if ( inputState.guessing==0 ) {
					resultado = new Boolean(true);
				}
				break;
			}
			case FALSO:
			{
				n4 = LT(1);
				match(FALSO);
				if ( inputState.guessing==0 ) {
					resultado = new Boolean(false);
				}
				break;
			}
			case IDENT:
			{
				id = LT(1);
				match(IDENT);
				if ( inputState.guessing==0 ) {
					
								
								if(tablaSimbolos.existeSimbolo(id.getText()))
								{
									String contenido = tablaSimbolos.getContenidoSimbolo(id.getText());
									
									if(contenido==null)
									{
										Error.visualizarError(1,id.getLine(),"ERROR: La variable no tiene asignado ningun valor "+id.getText());	
									}/*
									else
									{
											
											if(tipoDato.compareTo("real")==0)
											{
												resultado = new Double(contenido.toString()).doubleValue();
											}
											else if(tipoDato.compareTo("int")==0)
											{
												resultado = new Integer(contenido);
											}
											else // expresiones booleanas
												Error.visualizarError(1,id.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con expresiones booleanas");
									}*/
									
									if(contenido.matches("[0-9~.]"))
						  				{
						  				System.out.println("Entero");
						  				resultado = new Integer(contenido.toString()).intValue();
						  				}
						  			else if((Float)Float.parseFloat(contenido) instanceof Float)
						  				{
						  				System.out.println("flotante");
						  				resultado = new Float(contenido.toString()).floatValue();
						  				}
								
								}
								else 
									Error.visualizarError(1,id.getLine(),"ERROR: la variable no ha sido declarada "+id.getText());
								
									
							
				}
				break;
			}
			case PAR_IZQ:
			{
				match(PAR_IZQ);
				resultado=expr_aritmetica();
				match(PAR_DER);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		return resultado;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"VAR",
		"IDENT",
		"FIN_INSTRUCCION",
		"OP_ASIG",
		"SEPARA",
		"OP_O",
		"OP_NO",
		"OP_MAYOR",
		"OP_MENOR",
		"OP_IG",
		"OP_DIST",
		"OP_POT",
		"OP_MOD",
		"OP_SUM",
		"OP_RES",
		"OP_MUL",
		"OP_DIV",
		"NUMERO",
		"REAL",
		"VERDADERO",
		"FALSO",
		"PAR_IZQ",
		"PAR_DER",
		"FIN_INTERPRETE"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 134217776L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 134217760L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 67140162L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 514L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 67172930L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 67238466L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 67631682L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 69204546L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	
	}
