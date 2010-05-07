package sticky;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import antlr.ANTLRException;

public abstract class Procesador {

  private static String output;

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

    System.out.println(output);
  }

  public static String run(String code) {

    output = "Processing...\n";
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

  public static void println(String str) {
    output += str + '\n';
  }
}