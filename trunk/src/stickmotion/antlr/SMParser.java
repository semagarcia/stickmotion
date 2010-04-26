// $ANTLR : "syntax.g" -> "SMParser.java"$

	package stickmotion.antlr;

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

public class SMParser extends antlr.LLkParser       implements SMParserTokenTypes
 {

		Token e1= new Token();
	
protected SMParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public SMParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected SMParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public SMParser(TokenStream lexer) {
  this(lexer,1);
}

public SMParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final void sentences() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop75:
			do {
				if ((LA(1)==REAL||LA(1)==10)) {
					expression();
					match(4);
				}
				else {
					break _loop75;
				}
				
			} while (true);
			}
			
				   		System.out.println("expresiones reconocidas");
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			exp_mult();
			{
			_loop79:
			do {
				if ((LA(1)==5||LA(1)==6)) {
					{
					switch ( LA(1)) {
					case 5:
					{
						match(5);
						break;
					}
					case 6:
					{
						match(6);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					exp_mult();
				}
				else {
					break _loop79;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void exp_mult() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			exp_base();
			{
			_loop83:
			do {
				if ((LA(1)==7||LA(1)==8)) {
					{
					switch ( LA(1)) {
					case 7:
					{
						match(7);
						break;
					}
					case 8:
					{
						match(8);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					exp_base();
				}
				else {
					break _loop83;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void exp_base() throws RecognitionException, TokenStreamException {
		
		Token  e1 = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case REAL:
			{
				e1 = LT(1);
				match(REAL);
				System.out.println(e1);
				break;
			}
			case 10:
			{
				match(10);
				expression();
				match(11);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\";\"",
		"\"+\"",
		"\"-\"",
		"\"*\"",
		"\"/\"",
		"REAL",
		"\"(\"",
		"\")\""
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2064L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 2160L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 2544L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	
	}
