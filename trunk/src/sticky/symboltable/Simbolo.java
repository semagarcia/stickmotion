package sticky.symboltable;

/**
 * Clase perteneciente al paquete tablasimbolos. Representa a un simbolo de la
 * tabla de simbolos.
 * 
 * @author GrupoStickman
 * 
 */
class Simbolo {

  private String nombre; // Variable String que contiene el nombre del simbolo
  private String contenido; // Variable String que contiene el contenido del

  // simbolo.

  /**
   * Constructor de la clase Simbolo que inicializa los datos necesarios de la
   * clase.
   * 
   * @param n
   *          Objeto tipo String que contiene el nombre del simbolo.
   * @param c
   *          Objeto tipo String que contiene el contenido del simbolo.
   */
  Simbolo(String n, String c) {
    nombre = new String(n);
    if (c == null)
      contenido = new String();
    else
      contenido = new String(c);
  }

  /**
   * Constructor de la clase Simbolo que inicializa los datos necesarios de la
   * clase.
   */
  Simbolo() {
    nombre = new String();
    contenido = new String();
  }

  /**
   * Funcion que asigna un nombre al simbolo.
   * 
   * @param n
   *          Objeto tipo String que contiene el nombre del simbolo.
   */
  void setNombre(String n) {
    nombre = n;
  }

  /**
   * Funcion que devuelve el nombre del simbolo.
   * 
   * @return nombre Objeto tipo String de la clase Simbolo.
   */
  String getNombre() {
    return nombre;
  }

  /**
   * Funcion que asigna el contenido del simbolo.
   * 
   * @param c
   *          Objeto tipo String que contiene el contenido del s�mbolo.
   */
  void setContenido(String c) {
    contenido = c;
  }

  /**
   * Funcion que devuelve el contenido del simbolo.
   * 
   * @return contenido Objeto tipo String que contiene el contenido del s�mbolo.
   */
  String getContenido() {
    return contenido;
  }

}