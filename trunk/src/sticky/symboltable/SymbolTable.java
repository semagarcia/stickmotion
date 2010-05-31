package sticky.symboltable;

import java.util.ArrayList;

import antlr.Token;

/**
 * Clase perteneciente al paquete tablasimbolos, que representa la tabla de
 * simbolos del analizador
 * 
 * @author GrupoStickman
 * 
 */

public class SymbolTable {

  private final ArrayList<Simbolo> tabla; // Contenedor ArrayList que representa

  // a la tabla de simbolos

  /**
   * Constructor de la clase TablaSimbolos que inicializa los datos necesarios
   * para la clase.
   */
  public SymbolTable() {
    tabla = new ArrayList<Simbolo>();

  }

  /**
   * Inserta un simbolo en la tabla de simbolos
   * 
   * @param token
   *          variable tipo token a insertar en la tabla de simbolos
   * @param tipovariable
   *          variable tipo String que contiene el tipo de simbolo a insertar.
   * @return true, si la operacion se ha realizado correctamente o false en caso
   *         contrario.
   */
  public boolean put(Token token) {
    // Comprobamos si el token que vamos a introducir en la tabla de simbolos
    // Ya existe
    if (existeSimbolo(token.getText()))
      return false;
    else {
      // Añadimos el simbolo a la tabla
      tabla.add(new Simbolo(token.getText(), null));
      return true;
    }

  }

  /**
   * Inserta un simbolo en la tabla de simbolos con un valor cuyo valor le ha
   * sido dado en su declaracion.
   * 
   * @param token
   *          variable tipo Token que contiene el token del simbolo.
   * @param tipovariable
   *          variable tipo String que contiene el tipo del simbolo.
   * @param valor
   *          Objeto tipo Object que contiene el valor del simbolo
   * @return true si la operacion se realiza correctamente y false en caso
   *         contrario.
   */

  public boolean put(Token token, Object valor) {

    // Comprobamos si existe el simbolo en la tabla de simbolos
    if (existeSimbolo(token.getText()))
      return false;
    else {
      // La asignacion es correcta podemos almacenar la variable en la tabla de
      // simbolos
      Simbolo s = new Simbolo(token.getText(), new String(valor.toString()));
      tabla.add(s);
      return true;

    }
  }

  /**
   * Inserta un simbolo en la tabla de simbolos.
   * 
   * @param nombre
   *          variable tipo Token que contiene el simbolo a insertar en la tabla
   *          de simbolos
   * @param tipovariable
   *          variable tipo Object que contiene el tipo de simbolo a insertar en
   *          la tabla de simbolos
   * @return devuelve TRUE cuando el proceso se ha realizado correctamente y
   *         FALSE en caso contrario
   */
  public boolean put(String nombre, Object tipovariable) {
    // Comprobamos si la variable que vamos a introducir ya esta declarada en la
    // tabla
    if (existeSimbolo(nombre))
      return false;
    else {
      tabla.add(new Simbolo(nombre, ""));
      return true;
    }
  }

  /**
   * Modifica el valor de un simbolo.
   * 
   * @param token
   *          Objeto tipo token que indica el token del simbolo.
   * @param valor
   *          Objeto tipo Object que contiene el valor del simbolo.
   * @return true si la operaciion se realiza correctamente y false en caso
   *         contrario.
   */
  public boolean set(Token token, Object valor) {
    // Comprobamos si el simbolo existe en la tabla de simbolos
    if (existeSimbolo(token.getText()) == false)
      return false;
    else {
      Simbolo simbolo = getSimbolo(token);

      simbolo.setContenido(valor.toString());
      return true;
    }
  }

  /**
   * Devuelve el contenido de un simbolo de la tabla de simbolos, cuyo nombre
   * viene designado por la variable String nombre pasada al metodo.
   * 
   * @param nombre
   *          variable tipo String que contiene el nombre del simbolo.
   * @return variable String que contiene el contenido del simbolo.
   */
  public String getContenidoSimbolo(String nombre) {
    String contenido = "";

    for (int i = 0; i < tabla.size(); i++)
      if (nombre.compareTo(tabla.get(i).getNombre()) == 0)
        contenido = tabla.get(i).getContenido();

    return contenido;
  }

  /**
   * Busca un simbolo en la tabla de simbolos.
   * 
   * @param nombre
   *          variable tipo String que contiene el nombre del simbolo.
   * @return true, si la operacion se ha realizado correctamente o false en caso
   *         contrario.
   */
  public boolean existeSimbolo(String nombre) {
    boolean existe = false;

    for (int i = 0; i < tabla.size(); i++)
      if (nombre.compareTo(tabla.get(i).getNombre()) == 0)
        existe = true;

    return existe;
  }

  /**
   * Devuelve un simbolo de la tabla de simbolos designado por el parametro
   * token
   * 
   * @param token
   *          Objeto tipo Token que designa el token del simbolo
   * @return El simbolo de la tabla de simbolos
   */
  public Simbolo getSimbolo(Token token) {
    Simbolo simbolo = null;
    String nombre = token.getText();

    for (int i = 0; i < tabla.size(); i++)
      if (nombre.compareTo(tabla.get(i).getNombre()) == 0)
        simbolo = tabla.get(i);

    return simbolo;
  }

  public String delSimbolo(Token token) {
    Simbolo simbolo = null;
    String nombre = token.getText();

    for (int i = 0; i < tabla.size(); i++)
      if (nombre.compareTo(tabla.get(i).getNombre()) == 0)
        simbolo = tabla.remove(i);

    return simbolo.getNombre();
  }

}