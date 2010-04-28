package sticky;

import java.io.FileInputStream;
import java.util.HashMap;

public class Procesador {

  public static void main(String args[]) {
    try {

      // Charges a file
      String file = "codigo.stk";
      FileInputStream fis = new FileInputStream(file);

      // Analyzes the characters of the file and makes the corresponding tokens
      Analex analex = new Analex(fis);

      // Analyzes the ordered tokens
      Anasint anasint = new Anasint(analex);

      // Calls to the initial rule of the parser
      anasint.instrucciones(new HashMap());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
