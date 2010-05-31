package sticky.symboltable;

import java.util.ArrayList;

import antlr.Token;

/**
 * Class representing the table of symbols that will be used by the Parser
 * (Anasint)
 * 
 */

public class SymbolTable {

  /**
   * ArrayList container that stores the symbol table
   */
  private final ArrayList<Simbolo> tabla;

  /**
   * Constructor of the class SymbolTable that initializes the needed data
   */
  public SymbolTable() {
    tabla = new ArrayList<Simbolo>();

  }

  /**
   * Inserts a symbol in the symbol table
   * 
   * @param token
   *          "Token" typed variable to insert in the symbol table
   */
  public boolean put(Token token) {
    // Check if the token to add to the table exists already
    if (existeSimbolo(token.getText()))
      return false;
    else {
      // Add the symbol to the table
      tabla.add(new Simbolo(token.getText(), null));
      return true;
    }

  }

  /**
   * Inserts a symbol in the symbol table with a value asigned at declaration
   * time
   * 
   * @param token
   *          "Token" typed parameter that contains the symbol token
   * @param value
   *          "Objet" typed parameter that contains the symbol value
   * @return true if the operation ends successfully, false otherwise
   */

  public boolean put(Token token, Object value) {

    // Check if the token to add to the table exists already
    if (existeSimbolo(token.getText()))
      return false;
    else {
      // La asignacion es correcta podemos almacenar la variable en la tabla de
      // simbolos
      Simbolo s = new Simbolo(token.getText(), new String(value.toString()));
      tabla.add(s);
      return true;

    }
  }

  /**
   * Inserts a symbol in the symbol table
   * 
   * @param name
   *          "Token" parameter that contains the symbol to insert
   * @param variableType
   *          "Object" typed parameter that contains the symbol type of the
   *          symbol to insert
   * @return true if the operation ends successfully, false otherwise
   */
  public boolean put(String name, Object variableType) {
    // Check if the variable to add to the table exists already
    if (existeSimbolo(name))
      return false;
    else {
      tabla.add(new Simbolo(name, ""));
      return true;
    }
  }

  /**
   * Modifies the value of a symbol
   * 
   * @param token
   *          "Token" typed parameter that contains the token of the symbol
   * @param value
   *          "Object" typed parameter that contains the symbol value
   * @return true if the operation ends successfully, false otherwise
   */
  public boolean set(Token token, Object value) {
    // Check if the symbol to add to the table exists already
    if (existeSimbolo(token.getText()) == false)
      return false;
    else {
      Simbolo simbolo = getSimbolo(token);

      simbolo.setContenido(value.toString());
      return true;
    }
  }

  /**
   * Returns the content of the symbol whith the given name in the symbol table
   * 
   * @param name
   *          "String" containing the symbol name
   * @return String contained in the symbol
   */
  public String getContenidoSimbolo(String name) {
    String contenido = "";

    for (int i = 0; i < tabla.size(); i++)
      if (name.compareTo(tabla.get(i).getNombre()) == 0)
        contenido = tabla.get(i).getContenido();

    return contenido;
  }

  /**
   * Searches for a symbol in the symbol table
   * 
   * @param name
   *          "String" containing the symbol name
   * @return true if the operation ends successfully, false otherwise
   */
  public boolean existeSimbolo(String name) {
    boolean existe = false;

    for (int i = 0; i < tabla.size(); i++)
      if (name.compareTo(tabla.get(i).getNombre()) == 0)
        existe = true;

    return existe;
  }

  /**
   * Devuelve un simbolo de la tabla de simbolos designado por el parametro
   * token
   * 
   * Returns a symbol from the symbol table designed by the given token
   * 
   * @param token
   *          "Token" that designs the symbol to return
   * @return Symbol from the symbol table to return
   */
  public Simbolo getSimbolo(Token token) {
    Simbolo simbolo = null;
    String nombre = token.getText();

    for (int i = 0; i < tabla.size(); i++)
      if (nombre.compareTo(tabla.get(i).getNombre()) == 0)
        simbolo = tabla.get(i);

    return simbolo;
  }

  /**
   * Method for deleting a symbol from the symbol table
   * 
   * @param token
   *          "Token" that designs the symbol to return
   * @return Name of the symbol. If the symbol was not found it returns null
   */
  public String delSimbolo(Token token) {
    Simbolo simbolo = null;
    String nombre = token.getText();

    for (int i = 0; i < tabla.size(); i++)
      if (nombre.compareTo(tabla.get(i).getNombre()) == 0)
        simbolo = tabla.remove(i);

    return simbolo.getNombre();
  }

}