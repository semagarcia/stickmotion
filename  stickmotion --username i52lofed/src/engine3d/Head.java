package engine3d;

import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.ColorCube;

/**
 * Class for the stickman's arms
 */
public class Head extends SMGroup {

  // size of the head
  private final float HEADSIZE = (float) 0.2;
  private final float LENGTH = (float) (HEADSIZE + 0.07);

  // Angle for the head
  private final Vector3f angle;

  /**
   * Constructor for the class Head
   */
  public Head() {
    super();

    angle = new Vector3f(0, 0, 0);
    ColorCube head = new ColorCube(HEADSIZE);

    addChild(head);
  }

  /**
   * Method for setting the rotation parameters
   * 
   * @param x
   * @param y
   * @param z
   */
  @Override
  public void rotation(float x, float y, float z) {
    super.rotation(x, y, z);
    // store the angle after using the rotation function
    angle.x = x;
    angle.y = y;
    angle.z = z;
  }

  /**
   * Method for setting the translation parameters
   * 
   * @param x
   * @param y
   * @param z
   */
  @Override
  public void translation(float x, float y, float z) {
    // Calculate the translation correction required due to the angle
    x += (float) ((Math.sin(angle.z)) * HEADSIZE / 2);
    z += (float) ((Math.sin(angle.x)) * HEADSIZE / 2);
    // Move the head up so that the center of coordinates is the point where it
    // will be attached to the body
    y += LENGTH;
    super.translation(x, y, z);
  }
}
