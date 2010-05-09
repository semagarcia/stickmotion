package sticky;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import antlr.ANTLRException;

public abstract class Procesador {

  private static String output = "";
  private static int debugMode = 2; // Por defecto 2 (lo muestra todo)

  public static void main(String[] args) {

    try {

      FileInputStream fis = new FileInputStream("codigo.stk");
      Analex analex = new Analex(fis);

      Anasint anasint = new Anasint(analex);
      anasint.instrucciones();

    } catch (ANTLRException ae) {
      System.err.println(ae.getMessage());
    } catch (FileNotFoundException fnfe) {
      System.err.println("No se encontró el fichero");
    }
  }

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
      System.err.println(ae.getMessage());
    }
    return output;
  }

  public static void println(int debugLevel, String str) {
    // debugLevel<0 -> Información vital (Mensajes de inicio y fin de sticky y
    // primitiva
    // mostrar() )
    // debugLevel == 0 -> Mensajes de error
    // debugLevel > 0 -> Mensajes Debug <debugLevel>.
    if (debugLevel > 0) {
      if (debugLevel <= debugMode) {
        str = "DEBUG " + debugLevel + ": " + str;
        output += str + '\n';
        System.out.println(output);
      }
    } else if (debugLevel == 0) {
      str = "ERROR: " + str;
      output += str + '\n';
      System.out.println(output);
    } else {
      // Este es necesario para la primitiva mostrar(), para que no se imprima
      // ni Error ni Debug delante
      output += str + '\n';
      System.out.println(output);
    }
  }

  public static void cambiarNivelDebug(int nivel) {
    debugMode = nivel;
  }

}