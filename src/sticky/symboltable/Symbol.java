package sticky.symboltable;

/**
 * Class representing a symbol from the symbol table
 * 
 */
class Symbol {

  /** Name of the symbol */
  private String nombre;
  /** Content of the symbol */
  private String contenido;

  /**
   * Constructor for Symbol class for initilizing the needed data
   * 
   * @param n
   *          String containing the symbol name
   * @param c
   *          Object containing the symbol content
   */
  Symbol(String n, String c) {
    nombre = new String(n);
    if (c == null)
      contenido = new String();
    else
      contenido = new String(c);
  }

  /**
   * Empty constructor for Symbol class
   */
  Symbol() {
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
   * Returns the symbol name
   * 
   * @return nombre String containing the symbol name
   */
  String getNombre() {
    return nombre;
  }

  /**
   * Sets the symbol content
   * 
   * @param c
   *          String containing the symbol content
   */
  void setContenido(String c) {
    contenido = c;
  }

  /**
   * Returns the symbol content
   * 
   * @return String containing the symbol content
   */
  String getContenido() {
    return contenido;
  }

}