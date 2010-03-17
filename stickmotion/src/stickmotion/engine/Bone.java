/**
 * 
 */
package stickmotion.engine;

/**
 * Clase genérica para la declaración de las secciones del stickman de la que
 * heredarán el resto de extremidades
 */
public class Bone {

  /**
   * Ángulo de inclinación o colatitud (de 0º a 360º) es el ángulo entre el eje
   * z y la línea que conforma el Bone
   */
  private double inclination;

  /** Máximo ángulo de inclinación */
  private final double inclinationMax;

  /**
   * Azimuth (de 0º a 180º) es el ángulo entre el eje X positivo y la línea de
   * proyección del bone con el plano XY.
   */
  private double azimuth;

  /** Máximo ángulo de azimuth */
  private final double azimuthMax;

  /** Longitud del Bone */
  private final double length;

  /** Padre del que este Bone surge */
  public Bone parent;

  /**
   * Constructor de la clase Bone
   * 
   * @param angle
   * @param size
   * @throws WrongSizeException
   */
  public Bone(double inclinationMax, double azimuthMax, double length)
      throws WrongSizeException {
    this.inclinationMax = inclinationMax;
    this.azimuthMax = azimuthMax;
    this.length = length;
  }

  /**
   * Método de asignación para la variable inclination
   * 
   * @param inclination
   *          El valor de inclination a asignar
   */
  public void setColatitud(double inclination) {
    this.inclination = inclination;
  }

  /**
   * Método de obtención de la inclinación
   * 
   * @return Devuelve el valor de inclinación
   */
  public double getInclination() {
    return inclination;
  }

  /**
   * Método de asignación para la azimuth
   * 
   * @param azimuth
   *          El valor de azimuth a asignar
   */
  public void setAzimuth(double azimuth) {
    this.azimuth = azimuth;
  }

  /**
   * Método de obtención de la azimuth
   * 
   * @return Devuelve el valor de azimuth
   */
  public double getAzimuth() {
    return azimuth;
  }

}
