package engine3d;

import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cylinder;

/**
 * Class for Stickman's body
 */
public class Stickman extends SMGroup {

  // size of the cylinder that emulates the central body
  private final float BODYH = 1;
  private final float BODYR = (float) 0.3;
  // size of the head
  private final float HEADSIZE = (float) 0.2;

  // Head
  private final SMGroup head;

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

  /**
   * Constructor for the class Stickman
   */
  public Stickman() {

    // Initial angle values
    rArmAngle = new Vector3f(0, 0, (float) 0.5);
    lArmAngle = new Vector3f(0, 0, (float) 0.5);
    rLegAngle = new Vector3f(0, 0, (float) 0.5);
    lLegAngle = new Vector3f(0, 0, (float) 0.5);
    headAngle = new Vector3f(0, 0, 0);

    // the body trunk doesn't have transformations, its transformations are done
    // directly in the arm
    Cylinder body = new Cylinder(BODYR, BODYH);

    // create the limbs
    rArm = new Arm();
    lArm = new Arm();
    rLeg = new Arm();
    lLeg = new Arm();

    // the head will be a moveable cube
    head = new SMGroup();
    head.addSMChild(new Cylinder(HEADSIZE, HEADSIZE));

    // adds body, limbs and head to the Stickman as children
    addSMChild(body);
    addSMChild(rArm);
    addSMChild(lArm);
    addSMChild(rLeg);
    addSMChild(lLeg);
    addSMChild(head);

    updateJoints();

  }

  /**
   * Method for updating the position and angle of the Stickman body joints
   */
  public void updateJoints() {

    // Right Arm
    rArm.rotation(rArmAngle.x, rArmAngle.y, rArmAngle.z);
    // Displace to be on the edge of the body cylinder
    rArm.translation(BODYR, 0, 0);

    // Left Arm
    lArm.rotation(-lArmAngle.x, -lArmAngle.y, -lArmAngle.z);
    // Displace to be on the edge of the body cylinder
    lArm.translation(-BODYR, 0, 0);

    // Right Leg
    rLeg.rotation(rLegAngle.x, rLegAngle.y, rLegAngle.z);
    // Displace to be on the edge and bottom of the body cylinder
    rLeg.translation(BODYR, -BODYH, 0);

    // Left Leg
    lLeg.rotation(-lLegAngle.x, -lLegAngle.y, -lLegAngle.z);
    // Displace to be on the edge and bottom of the body cylinder
    lLeg.translation(-BODYR, -BODYH, 0);

    // Head
    // applies the rotation and sets the correct position to the head
    head.rotation(headAngle.x, headAngle.y, headAngle.z);
    head
        .translation(
        // X axis correction for Z rotation
            (float) (Math.sin(headAngle.z) * HEADSIZE / 2),
            // Y axis correction for X and Z rotation
            (float) (0.6 + (Math.sin(headAngle.x) + Math.cos(headAngle.z))
                * HEADSIZE / 2),
            // Z axis correction for Y and X rotation
            (float) ((Math.sin(headAngle.y) + Math.cos(headAngle.x)) * HEADSIZE / 2));

  }
}
