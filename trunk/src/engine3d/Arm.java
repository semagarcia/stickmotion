package engine3d;

import javax.vecmath.Vector3f;

/**
 * Class for the stickman's arms
 */
public class Arm extends SMGroup {

  // size of the cylinders that emulates bones
  private final float HEIGHT = (float) 0.5;
  private final float RADIUS = (float) 0.05;

  // Angle for the joint between anterior and forearm
  private Vector3f foreAngle;

  // Forearm
  public SMGroup fore;

  /**
   * Constructor for the class Arm
   */
  public Arm() {
    super();

    // initial values
    foreAngle = new Vector3f(0, 0, 0);

    // anterior doesn't have transformations, its transformations are done
    // directly in the arm
    TransCylinder anterior = new TransCylinder(RADIUS, HEIGHT);

    // fore is a SMGroup because of the transformations applied
    fore = new SMGroup();
    fore.addChild(new TransCylinder(RADIUS, HEIGHT));

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
    fore.translation(0, -HEIGHT, 0);
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
