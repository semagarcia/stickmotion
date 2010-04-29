package engine3d;

import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cylinder;

/**
 * Class for the stickman's arms
 */
public class Arm extends SMGroup {

  // size of the cylinders that emulates bones
  public final float HEIGHT = (float) 0.5;
  public final float RADIUS = (float) 0.05;

  // Angle for the joint between anterior and forearm
  public Vector3f angle;

  /**
   * Constructor for the class Arm
   */
  public Arm() {
    super();
    angle = new Vector3f(0, 0, (float) 0.5);

    // anterior doesn't have transformations, its transformations are done
    // directly in the arm
    Cylinder anterior = new Cylinder(RADIUS, HEIGHT);

    // fore is a SMGroup because of the transformations applied
    SMGroup fore = new SMGroup();
    fore.addSMChild(new Cylinder(RADIUS, HEIGHT));

    // applies the rotation and it correction to the forearm
    fore.rotation(angle.x, angle.y, angle.z);
    fore.translation((float) (Math.sin(angle.z) * HEIGHT / 2), -HEIGHT,
        (float) (Math.sin(angle.x) * HEIGHT / 2));

    // adds the children to the arm
    addSMChild(fore);
    addSMChild(anterior);
  }

}
