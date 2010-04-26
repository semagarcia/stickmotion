/**
 * Archivo Frame3D.java
 */
package stickmotion.display;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.j3d.Canvas3D;

import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * Clase para la ventana que muestra la vista 3D
 */
@SuppressWarnings("serial")
public class Frame3D extends Frame {

  /**
   * 
   * Constructor de la clase Frame3D
   * 
   */
  public Frame3D() {
    super();
    this.setSize(800, 600);
    setLayout(new BorderLayout());

    // Crea el canvas3D
    Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
    this.add("Center", canvas3D);

    // Crea el universo en el canvas creado y carga la escena
    Universe3D u = new Universe3D(canvas3D);
    u.loadSceneGraph();
  }

  /**
   * Método estático para iniciar una ventana. Será llamado desde el programa
   * principal para iniciar la aplicación.
   */
  static public void start() {
    Frame frame = new Frame3D();
    frame.setSize(800, 600);
    frame.setVisible(true);

    // Al cerrar la ventana salimos del programa
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent evt) {
        System.exit(0);
      }
    });
  }
}
