/**
 * 
 */
package stickmotion.engine;

/**
 * Clase genérica para la declaración de las secciones del stickman de la que
 * heredarán el resto de extremidades
 */
public class Limb {

  private double angle;
  private double size;

  /**
   * Constructor de la clase Limb
   * 
   * @param angle
   * @param size
   * @throws WrongSizeException
   */
  public Limb(double angle, double size) throws WrongSizeException {
    setAngle(angle);
    setSize(size);
  }

  /**
   * Método de asignación del tamaño
   * 
   * @param size
   *          El valor en tanto por uno del tamaño a asignar
   * @throws WrongSizeException
   */
  public void setSize(double size) throws WrongSizeException {
    // El tanto por uno de tamaño debe ser entre 0 y 1
    if (size > 1 || 0 < size)
      throw new WrongSizeException();
    this.size = size;
  }

  /**
   * Método de obtención del tamaño
   * 
   * @return devuelve el valor de tamaño
   */
  public double getSize() {
    return size;
  }

  /**
   * Método de asignación del ángulo
   * 
   * @param angle
   *          El valor del ángulo a asignar
   */
  public void setAngle(double angle) {
    this.angle = angle;
  }

  /**
   * Método de obtención del ángulo
   * 
   * @return devuelve el valor de ángulo
   */
  public double getAngle() {
    return angle;
  }

}
