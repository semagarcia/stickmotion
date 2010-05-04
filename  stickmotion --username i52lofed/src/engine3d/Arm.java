package engine3d;

import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cylinder;

/**
 * Class for the stickman's arms
 */
public class Arm extends SMGroup {

  // size of the cylinders that emulates bones
  private final float HEIGHT = (float) 0.5;
  private final float RADIUS = (float) 0.05;

  // Angle for the joint between anterior and forearm
  // It's set to public so that we can change it for flexing the arm.
  public Vector3f foreAngle;
  // Angle for the whole arm
  private final Vector3f armAngle;

  // Forearm
  private final SMGroup fore;

  /**
   * Constructor for the class Arm
   */
  public Arm() {
    super();

    // initial values
    foreAngle = new Vector3f(0, 0, (float) 0.2);
    armAngle = new Vector3f(0, 0, (float) 0.2);

    // anterior doesn't have transformations, its transformations are done
    // directly in the arm
    Cylinder anterior = new Cylinder(RADIUS, HEIGHT);

    // fore is a SMGroup because of the transformations applied
    fore = new SMGroup();
    fore.addChild(new Cylinder(RADIUS, HEIGHT));

    // adds the children to the arm
    addChild(fore);
    addChild(anterior);

    updateJoint();
  }

  /**
   * Method for updating the position and angle of the forearm joint
   */
  public void updateJoint() {
    // applies the rotation
    fore.rotation(foreAngle.x, foreAngle.y, foreAngle.z);
    // translates the forearm the correct the angle displacement
    fore.translation((float) (Math.sin(foreAngle.z) * HEIGHT / 2), -HEIGHT,
        (float) (Math.sin(foreAngle.x) * HEIGHT / 2));
  }

  /**
   * Method for setting the translation parameters and adjusting them to have
   * the center on the point for attachment to the body.
   * 
   * @param x
   * @param y
   * @param z
   */
  @Override
  public void translation(float x, float y, float z) {
    // Apply the correction due to the angle
    x += (float) ((Math.sin(armAngle.z)) * HEIGHT / 2);
    z += (float) ((Math.sin(armAngle.x)) * HEIGHT / 2);
    // Move the arm down so that the center of coordinates is the point where it
    // will be attached to the body
    y -= HEIGHT / 2;
    super.translation(x, y, z);
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
    armAngle.x = x;
    armAngle.y = y;
    armAngle.z = z;
  }

  /**
   * Method for setting the value of the foreAngle
   * 
   * @param foreAngle
   *          Value of foreAngle to set
   */
  public void setForeAngle(Vector3f foreAngle) {
    this.foreAngle = foreAngle;
    updateJoint();
  }

}
