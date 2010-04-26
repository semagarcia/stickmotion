import java.io.FileInputStream;
import java.io.FileNotFoundException;

import stickmotion.antlr.SMLexer;
import stickmotion.antlr.SMParser;
import antlr.ANTLRException;

/**
 * Archivo FileReader.java
 */

/**
 *
 */
public class FileReader {

  /**
   * Método para TODO
   * 
   * @param args
   */
  public static void main(String[] args) {
    try {

      args[0] = "FileInput.txt";
      FileInputStream fis = new FileInputStream(args[0]);
      SMLexer lexer = new SMLexer(fis);
      lexer.setFilename(args[0]);

      // lexer.setTokenObjectClass("analizadorANTLR.MiToken");
      /*
       * Token token = analex.nextToken(); while(token.getType() !=
       * Token.EOF_TYPE) { System.out.println(token); token =
       * analex.nextToken(); }
       */

      SMParser parser = new SMParser(lexer);
      parser.sentences();
      parser.setFilename(args[0]);

    } catch (ANTLRException ae) {
      System.err.println(ae.getMessage());
    } catch (FileNotFoundException fnfe) {
      System.err.println("No se encontró el fichero");
    }
  }
}
