import stickmotion.display.Frame3D;

/**
 * Clase principal de la aplicación
 * 
 * Desde aquí se realiza la gestión de los argumentos de linea de comandos que
 * recibirá el programa
 */
public class Main {

  /**
   * Método principal
   * 
   * @param args
   *          argumentos de linea de comandos
   */
  public static void main(String[] args) {
    if (args.length > 2) // ya se cambiará
      System.out.println("Sintaxis: stickmotion [opciones] [accion]");
    else
      Frame3D.start();
  }
}
