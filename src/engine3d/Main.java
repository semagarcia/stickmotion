package engine3d;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Canvas3D;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;

// main class of the program
public class Main extends Applet {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  // shows an applet with the scene
  public Main() {
    setLayout(new BorderLayout());

    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas3D = new Canvas3D(config);
    add("Center", canvas3D);

    new Scene(canvas3D);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    new MainFrame(new Main(), 800, 600);
  }
}
