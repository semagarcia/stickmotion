/**
 * Archivo Keybindings.java
 */
package stickmotion.display;

import java.awt.event.KeyEvent;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;

/**
 *
 */
public class Keybindings extends KeyNavigatorBehavior {

  /**
   * Constructor de la clase Keybindings
   * 
   * @param arg0
   */
  public Keybindings(TransformGroup trans) {
    super(trans);

    // Realizar una primera transformación de inclinación
    // para que la escena pueda visualizarse en perspectiva
    Transform3D T3D = new Transform3D();
    T3D.setTranslation(new Vector3f(0.0f, 0.3f, 0.0f));
    trans.setTransform(T3D);

    // Determina los límites de movimiento
    setSchedulingBounds(new BoundingSphere(new Point3d(), 100.0));

    // La cámara puede ser bloqueada por obstaculos
    // setCollidable(true);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() != KeyEvent.VK_ESCAPE) {
      System.out.println("Saliendo...");
      System.exit(0);
    }
  }

}
