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

  // Right and Left Arm Angles
  private final Vector3f _rArmAngle;
  private final Vector3f _lArmAngle;
  // Right and Left Leg Angles
  private final Vector3f _rLegAngle;
  private final Vector3f _lLegAngle;
  // head Angle
  private final Vector3f _headAngle;

  // Limbs
  Arm rArm, lArm, rLeg, lLeg;
  // Head
  SMGroup head;

  /**
   * Constructor for the class Stickman
   */
  public Stickman() {

    // Initial angle values
    _rArmAngle = new Vector3f(0, 0, (float) 0.5);
    _lArmAngle = new Vector3f(0, 0, (float) 0.5);
    _rLegAngle = new Vector3f(0, 0, (float) 0.5);
    _lLegAngle = new Vector3f(0, 0, (float) 0.5);
    _headAngle = new Vector3f(0, 0, 0);

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
    addChild(body);
    addChild(rArm);
    addChild(lArm);
    addChild(rLeg);
    addChild(lLeg);
    addChild(head);

    updateJoints();

  }

  /**
   * Method for updating the position and angle of the Stickman body joints
   */
  public void updateJoints() {

    // Right Arm
    rArm.rotation(_rArmAngle.x, _rArmAngle.y, _rArmAngle.z);
    // Displace to be on the edge of the body cylinder
    rArm.translation(BODYR, 0, 0);

    // Left Arm
    lArm.rotation(-_lArmAngle.x, -_lArmAngle.y, -_lArmAngle.z);
    // Displace to be on the edge of the body cylinder
    lArm.translation(-BODYR, 0, 0);

    // Right Leg
    rLeg.rotation(_rLegAngle.x, _rLegAngle.y, _rLegAngle.z);
    // Displace to be on the edge and bottom of the body cylinder
    rLeg.translation(BODYR, -BODYH, 0);

    // Left Leg
    lLeg.rotation(-_lLegAngle.x, -_lLegAngle.y, -_lLegAngle.z);
    // Displace to be on the edge and bottom of the body cylinder
    lLeg.translation(-BODYR, -BODYH, 0);

    // Head
    // applies the rotation and sets the correct position to the head
    head.rotation(_headAngle.x, _headAngle.y, _headAngle.z);
    head
        .translation(
        // X axis correction for Z rotation
            (float) (Math.sin(_headAngle.z) * HEADSIZE / 2),
            // Y axis correction for X and Z rotation
            (float) (0.6 + (Math.sin(_headAngle.x) + Math.cos(_headAngle.z))
                * HEADSIZE / 2),
            // Z axis correction for Y and X rotation
            (float) ((Math.sin(_headAngle.y) + Math.cos(_headAngle.x))
                * HEADSIZE / 2));

  }
}
