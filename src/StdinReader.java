import java.io.BufferedReader;
import java.io.InputStreamReader;

import stickmotion.antlr.SMLexer;
import stickmotion.antlr.SMParser;
import antlr.ANTLRException;

/**
 * Archivo FileReader.java
 */

/**
 *
 */
public class StdinReader {

  /**
   * Método para TODO
   * 
   * @param args
   */
  public static void main(String[] args) {

    InputStreamReader converter = new InputStreamReader(System.in);
    BufferedReader in = new BufferedReader(converter);

    try {
      while (true) {

        SMLexer lexer = new SMLexer(in);

        // lexer.setTokenObjectClass("analizadorANTLR.MiToken");

        antlr.Token token = lexer.nextToken();
        if (token.getType() == antlr.Token.EOF_TYPE) {
          System.out.println("Saliendo");
          break;
        }

        SMParser parser = new SMParser(lexer);
        parser.sentences();

      }
    } catch (ANTLRException ae) {
      System.err.println(ae.getMessage());
    }
  }
}
