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

    updatePositions();

  }

  /**
   * Method for updating the position and angle of every articulation of the
   * Stickman
   */
  public void updatePositions() {

    // applies the rotation and sets the correct position to the right arm
    rArm.rotation(_rArmAngle.x, _rArmAngle.y, _rArmAngle.z);
    rArm.translation(
        (float) (BODYR + (Math.sin(rArm.angle.z) * rArm.HEIGHT / 2)),
        (float) (Math.cos(rArm.angle.z) * rArm.HEIGHT / 2), 0);

    // applies the rotation and sets the correct position to the left arm
    lArm.rotation(-_lArmAngle.x, -_lArmAngle.y, -_lArmAngle.z);
    lArm.translation(
        (float) -(BODYR + Math.sin(lArm.angle.z) * lArm.HEIGHT / 2),
        (float) (Math.cos(lArm.angle.z) * lArm.HEIGHT / 2), 0);

    // applies the rotation and sets the correct position to the right leg
    rLeg.rotation(_rLegAngle.x, _rLegAngle.y, _rLegAngle.z);
    rLeg.translation(
        (float) (BODYR + (Math.sin(rLeg.angle.z) * rLeg.HEIGHT / 2)),
        (float) (-BODYH + Math.cos(rLeg.angle.z) * rLeg.HEIGHT / 2), 0);

    // applies the rotation and sets the correct position to the left leg
    lLeg.rotation(-_lLegAngle.x, -_lLegAngle.y, -_lLegAngle.z);
    lLeg.translation(
        (float) -(BODYR + (Math.sin(lLeg.angle.z) * lLeg.HEIGHT / 2)),
        (float) (-BODYH + Math.cos(lLeg.angle.z) * lLeg.HEIGHT / 2), 0);

    // applies the rotation and sets the correct position to the head
    head.rotation(_headAngle.x, _headAngle.y, _headAngle.z);
    head.translation((float) (Math.sin(_headAngle.z) * HEADSIZE / 2),
        (float) (0.6 + Math.cos(_headAngle.z) * HEADSIZE / 2), 0);

  }
}
