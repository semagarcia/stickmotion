package sticky;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import antlr.ANTLRException;

/**
 * Class for processing the Sticky code and generating movements in the
 * Stickmotion scene.
 * 
 */
public abstract class Processor {

  private static String output = "";
  private static int debugMode = 1; // Por defecto 2 (lo muestra todo)

  /**
   * Main Method for the processor to be run as an standalone program (module)
   * 
   * @param args
   *          command line arguments
   */
  public static void main(String[] args) {

    debugMode = 2;
    try {

      FileInputStream fis = new FileInputStream("codigo.stk");
      Analex analex = new Analex(fis);

      Anasint anasint = new Anasint(analex);
      anasint.instrucciones();

    } catch (ANTLRException ae) {
      Processor.println(0, ae.getMessage());
      System.err.println(ae.getMessage());

    } catch (FileNotFoundException fnfe) {
      Processor.println(0, fnfe.getMessage());
      System.err.println("No se encontró el fichero");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

  }

  /**
   * Method for processing some given code and running it, displaying output
   * with the detail given by the debug level. It will also update the
   * Stickmotion scene to perform the animations described by the code.
   * 
   * @param code
   *          String containing the Sticky Code to be run in the processor
   * @param debug
   *          Number for the level of detail of the output information to get: 0
   *          for Error information only; 1 for some information messages; 2 for
   *          more detailed auxiliary information
   * @return String with the output information from the processing
   */
  public static String run(String code, int debug) {

    debugMode = debug;
    output = "";
    try {

      InputStream is = com.sun.xml.internal.ws.util.xml.XmlUtil
          .getUTF8Stream(code);
      Analex analex = new Analex(is);

      Anasint anasint = new Anasint(analex);
      anasint.instrucciones();

    } catch (ANTLRException ae) {
      Processor.println(0, ae.getMessage());
      System.err.println(ae.getMessage());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return output;
  }

  /**
   * Method for storing a line to be displayed as output of the processing,
   * stablished inside different debug modes.
   * 
   * @param debugLevel
   *          Debug level for the message to be shown in
   * @param str
   *          Message to show if the given menu level is active
   */
  public static void println(int debugLevel, String str) {
    // debugLevel<0 -> Información vital (Mensajes de inicio y fin de sticky y
    // primitiva
    // mostrar() )
    // debugLevel == 0 -> Mensajes de error
    // debugLevel > 0 -> Mensajes Debug <debugLevel>.
    if (debugLevel > 0) {
      if (debugLevel <= debugMode)
        str = "DEBUG " + debugLevel + ": " + str;
      output += str + '\n';
      System.out.println(output);
    } else if (debugLevel == 0) {
      str = "ERROR" + ": " + str;
      output += str + '\n';
      System.out.println(output);
    } else {
      // Este es necesario para la primitiva mostrar(), para que no se imprima
      // ni Error ni Debug delante
      output += str + '\n';
      System.out.println(output);
    }
  }

  /**
   * Method for changing the Debug mode
   * 
   * @param level
   *          number for the level of detail wanted for the debug messages: 0
   *          for Error information only; 1 for some information messages; 2 for
   *          more detailed auxiliary information
   */
  public static void setDebugMode(int level) {
    debugMode = level;
  }

}