// $ANTLR : "anasint.g" -> "Anasint.java"$

	package sticky;
	import java.util.HashMap;

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

protected Anasint(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public Anasint(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected Anasint(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public Anasint(TokenStream lexer) {
  this(lexer,2);
}

public Anasint(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
}

	public final void instrucciones(
		HashMap vars
	) throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop374:
			do {
				if ((LA(1)==VAR||LA(1)==SUP)) {
					sentencia(vars);
				}
				else {
					break _loop374;
				}
				
			} while (true);
			}
			System.out.println("Reconocido. HashMap: "+vars);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void sentencia(
		HashMap vars
	) throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case VAR:
			{
				declaracion(vars);
				break;
			}
			case SUP:
			{
				eliminacion(vars);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(FIN_INSTRUCCION);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void declaracion(
		HashMap vars
	) throws RecognitionException, TokenStreamException {
		
		Token  i = null;
		Token  c = null;
		Token  e = null;
		Token  r = null;
		Token  v = null;
		Token  f = null;
		Token  ii = null;
		
		try {      // for error handling
			match(VAR);
			i = LT(1);
			match(IDENT);
			{
			switch ( LA(1)) {
			case OP_ASIG:
			{
				match(OP_ASIG);
				{
				switch ( LA(1)) {
				case CADENA:
				{
					c = LT(1);
					match(CADENA);
					break;
				}
				case ENTERO:
				{
					e = LT(1);
					match(ENTERO);
					break;
				}
				case REAL:
				{
					r = LT(1);
					match(REAL);
					break;
				}
				case VERDADERO:
				{
					v = LT(1);
					match(VERDADERO);
					break;
				}
				case FALSO:
				{
					f = LT(1);
					match(FALSO);
					break;
				}
				case IDENT:
				{
					ii = LT(1);
					match(IDENT);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case FIN_INSTRUCCION:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			
						if(c!=null)
								vars.put(i.getText(),c.getText());  //se asigna una cadena
						else
							if(e!=null)
								vars.put(i.getText(),new Integer(e.getText()));  //se asigna un entero
							else
								if(r!=null)
									vars.put(i.getText(),new Float(r.getText()));  //se asigna un real
								else
									if(v!=null)
										vars.put(i.getText(),true);  //se asigna V
									else
										if(f!=null)
											vars.put(i.getText(),false);  //se asigna F
										else
											if(ii!=null)
												vars.put(i.getText(),vars.get(ii.getText()));  // asigna el valor de otra variable
											else
												vars.put(i.getText(),null);  // asigna el valor de otra variable
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void eliminacion(
		HashMap vars
	) throws RecognitionException, TokenStreamException {
		
		Token  i = null;
		
		try {      // for error handling
			match(SUP);
			i = LT(1);
			match(IDENT);
			
					vars.remove(i.getText());
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"FIN_INSTRUCCION",
		"VAR",
		"IDENT",
		"OP_ASIG",
		"CADENA",
		"ENTERO",
		"REAL",
		"VERDADERO",
		"FALSO",
		"SUP"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 8226L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 16L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	
	}
