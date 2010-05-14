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

    Scene scene = new Scene(canvas3D);

    scene.reset();

    // A couple of animation examples

    scene.setTime(1000);
    // Crappy walking animation
    for (int i = 0; i < 20; i++) {
      // Move right Leg forward (z rot)
      scene.rotateRLeg(0, (float) (Math.PI / 3), 500);
      // Move left Leg backwards (z rot)
      scene.rotateLLeg(0, (float) -(Math.PI / 3), 500);
      // Flex foreLegs
      scene.flexRLeg((float) Math.PI / 3, 500);
      scene.flexLLeg((float) Math.PI / 3, 500);
      // Next second
      scene.addTime(500);
      // Un-Flex foreLegs
      scene.flexRLeg((float) -Math.PI / 3, 500);
      scene.flexLLeg((float) -Math.PI / 3, 500);
      // Move right Leg backwards (z rot)
      scene.rotateRLeg(0, (float) -(Math.PI / 3), 500);
      // Move left Leg forward (z rot)
      scene.rotateLLeg(0, (float) (Math.PI / 3), 500);
      scene.addTime(500);
    }

    int d = 500;
    scene.setTime(1000);
    // Rotate the arms to move sideways
    scene.rotateRArm((float) Math.PI / 2, 0, 50);
    scene.rotateLArm((float) Math.PI / 2, 0, 50);
    // Try to fly
    for (int i = 0; i < 100; i++) {
      // raise arms
      scene.rotateRArm(0, (float) Math.PI / 2, d);
      scene.rotateLArm(0, (float) Math.PI / 2, d);
      // flex forearms
      scene.flexRArm((float) Math.PI / 3, d);
      scene.flexLArm((float) Math.PI / 3, d);
      // next step
      scene.addTime(d);
      // un-Flex forearms
      scene.flexRArm((float) -Math.PI / 3, d);
      scene.flexLArm((float) -Math.PI / 3, d);
      // Low both Arms
      scene.rotateRArm(0, (float) -Math.PI / 2, d);
      scene.rotateLArm(0, (float) -Math.PI / 2, d);
      scene.addTime(d);
    }

    scene.start();

  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    new MainFrame(new Main(), 800, 600);
  }
}
