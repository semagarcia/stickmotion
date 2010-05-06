package engine3d;

import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cylinder;

/**
 * Class for Stickman's body
 */
public class Stickman extends SMGroup {

  // size of the cylinder that emulates the central body
  private final float BODYH = (float) 1.0;
  private final float BODYR = (float) 0.2;

  // Right and Left Arm Angles
  public Vector3f rArmAngle;
  public Vector3f lArmAngle;
  // Right and Left Leg Angles
  public Vector3f rLegAngle;
  public Vector3f lLegAngle;
  // head Angle
  public Vector3f headAngle;

  // Limbs
  // They are public so that they can be flexed and updated independently
  public Arm rArm, lArm, rLeg, lLeg;

  // Head
  public Head head;

  /**
   * Constructor for the class Stickman
   */
  public Stickman() {

    // Initial angle values
    rArmAngle = new Vector3f(0, 0, 0);
    lArmAngle = new Vector3f(0, 0, 0);
    rLegAngle = new Vector3f(0, 0, 0);
    lLegAngle = new Vector3f(0, 0, 0);
    headAngle = new Vector3f(0, 0, 0);

    // the body trunk doesn't have transformations, the transformations are done
    // directly to the stickman
    Cylinder body = new Cylinder(BODYR, BODYH);

    // create the limbs
    rArm = new Arm();
    lArm = new Arm();
    rLeg = new Arm();
    lLeg = new Arm();

    // create head
    head = new Head();

    // adds body, limbs and head to the Stickman as children
    addChild(body);
    addChild(rArm);
    addChild(lArm);
    addChild(rLeg);
    addChild(lLeg);
    addChild(head);

    // // Initial position
    // rArm.setForeAngle(new Vector3f(0, 0, (float) 0.5));
    // lArm.setForeAngle(new Vector3f(0, 0, (float) -0.5));
    // rLeg.setForeAngle(new Vector3f(0, 0, (float) -0.2));
    // lLeg.setForeAngle(new Vector3f(0, 0, (float) 0.2));

    updateJoints();
  }

  /**
   * Method for updating the position and angle of the Stickman body joints
   */
  public void updateJoints() {

    // Right Arm
    rArm.rotation(rArmAngle.x, rArmAngle.y, rArmAngle.z);
    // Displace to be on the edge of the body cylinder
    rArm.translation(BODYR, BODYH / 2, 0);

    // Left Arm
    lArm.rotation(lArmAngle.x, (float) Math.PI + lArmAngle.y, lArmAngle.z);
    // Displace to be on the edge of the body cylinder
    lArm.translation(-BODYR, BODYH / 2, 0);

    // Right Leg
    rLeg.rotation(rLegAngle.x, (float) Math.PI + rLegAngle.y, rLegAngle.z);
    // Displace to be on the edge and bottom of the body cylinder
    rLeg.translation(BODYR, -(BODYH / 2), 0);

    // Left Leg
    lLeg.rotation(lLegAngle.x, (float) Math.PI + lLegAngle.y, lLegAngle.z);
    // Displace to be on the edge and bottom of the body cylinder
    lLeg.translation(-BODYR, -(BODYH / 2), 0);

    // Head
    // applies the rotation and sets the correct position to the head
    head.rotation(headAngle.x, headAngle.y, headAngle.z);
    head.translation(0, BODYH / 2, 0);
  }
}
