package error;

/**
 * Clase perteneciente al paquete error. Esta clase representa a los tipos de
 * errores que nuestro analizador mostrará al usuario.
 * 
 * @author GrupoStickman
 * 
 */
public class Error {

  /**
   * Funcion que muestra al usuario que se ha producido un error y en que linea
   * del programa se ha cometido.
   * 
   * @param tipo
   *          variable tipo int que contiene el codigo del tipo de error
   * @param linea
   *          variable tipo int que contiene el numero de linea en el cual se ha
   *          producido el error.
   * @param mensaje
   *          Objeto tipo String que contiene el mensaje que se le mostrara al
   *          usuario.
   */
  public static void visualizarError(int tipo, int linea, String mensaje) {
    switch (tipo) {
    case 1:
      System.err.println("Error en la linea " + linea + ": " + mensaje);
      break;

    case 2:
      System.err.println("Error: " + mensaje);
      break;

    case 3:
      System.err.println("Error en la linea: " + linea + ": " + mensaje);
      System.err.println("EJECUCION ABORTADA");
      System.exit(1);
      break;

    }
  }

  /**
   * Funcion que muestra al usuario que tipo de error se ha cometido y en que
   * linea.
   * 
   * @param tipo
   *          variable tipo int que contiene el codigo del error.
   * @param linea
   *          variable tipo int que contiene el numero de la linea de programa,
   *          donde se ha cometido el error.
   */
  public static void errorExpresion(Integer tipo, Integer linea) {
    String mensaje = new String();
    mensaje = "Error en la linea " + linea + ":";
    switch (tipo) {
    case 0:
      mensaje += "Division por cero";
      break;
    case 1:
      mensaje += "No se ha podido evaluar la expresion logica";
      break;
    case 2:
      mensaje += "No se ha podido evaluar la expresion relacional";
      break;
    case 3:
      mensaje += "No se ha podido evaluar la expresion aritmetica";
      break;
    }
  }

}