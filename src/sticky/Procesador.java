package sticky;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import antlr.ANTLRException;

public class Procesador {

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

}