package engine3d;

import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * This class is a Group that contains a group of objects and its
 * transformations. Also have functions to add interpolations.
 */
public class SMGroup extends Group {

  // Area to be rendered in the animations. Specifies the programmed region
  private static final BoundingSphere _bounds = new BoundingSphere();

  // Statics initial transformations
  private final TransformGroup _rotation;
  private final TransformGroup _translation;
  private final TransformGroup _scale;

  // Node where the sons are going to be added
  private final BranchGroup _child;

  /**
   * Constructor that creates a group with its transformations
   * 
   */
  public SMGroup() {
    _rotation = new TransformGroup();
    _translation = new TransformGroup();
    _scale = new TransformGroup();
    _child = new BranchGroup();

    // Constructs the tree of the SMGroup
    super.addChild(_translation);
    _translation.addChild(_rotation);
    _rotation.addChild(_scale);
    _scale.addChild(_child);
  }

  /**
   * Method for adding a child to the tree so that transformations can be
   * performed over it.
   * 
   * @param node
   */
  @Override
  public void addChild(Node node) {
    _child.addChild(node);
  }

  /**
   * Method for setting the translation parameters
   * 
   * @param x
   * @param y
   * @param z
   */
  public void translation(float x, float y, float z) {
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3f(x, y, z));
    _translation.setTransform(translate);
  }

  /**
   * Method for setting the rotation parameters
   * 
   * @param x
   * @param y
   * @param z
   */
  public void rotation(float x, float y, float z) {
    Transform3D rotX = new Transform3D();
    Transform3D rotY = new Transform3D();
    Transform3D rotZ = new Transform3D();
    rotX.rotX(x);
    rotY.rotY(y);
    rotZ.rotZ(z);

    rotY.mul(rotZ);
    rotX.mul(rotY);

    _rotation.setTransform(rotX);
  }

  /**
   * Method for setting the scale parameters
   * 
   * @param x
   * @param y
   * @param z
   */
  public void scale(double x, double y, double z) {
    Transform3D scale = new Transform3D();
    scale.setScale(new Vector3d(x, y, z));
    _scale.setTransform(scale);
  }

  /**
   * Adds a SMRotationInterpolator Behavior to the SMGroup
   * 
   * @param duration
   * @param start
   * @param rotXAxis
   *          Angle about x in which the axis of rotation is going to be rotated
   * @param rotYAxis
   *          Angle about y in which the axis of rotation is going to be rotated
   * @param rotZAxis
   *          Angle about z in which the axis of rotation is going to be rotated
   * @param startAngle
   * @param finsishAngle
   */
  public void addRotationAnim(long duration, long start, double rotXAxis,
      double rotYAxis, double rotZAxis, float startAngle, float finishAngle) {
    // Creates a necessary TransformGroup for the SMRotationInterpolator
    TransformGroup tg = new TransformGroup();

    // Adds the animation between the child and it Parent
    Group parent = (Group) _child.getParent();
    parent.removeChild(_child);
    tg.addChild(_child);
    parent.addChild(tg);

    // Allows writing on TransfomGroup
    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

    // Creates an Alpha function
    Alpha rotationAlpha = new Alpha(1, duration);
    rotationAlpha.setStartTime(System.currentTimeMillis() + start);

    // Calculates the axis of rotation
    Transform3D rX = new Transform3D();
    Transform3D rY = new Transform3D();
    Transform3D rZ = new Transform3D();
    rX.rotX(rotXAxis);
    rY.rotY(rotYAxis);
    rZ.rotZ(rotZAxis);
    rY.mul(rZ);
    rX.mul(rY);

    // Creates the SMRotationInterpolator
    SMRotationInterpolator rotator = new SMRotationInterpolator(rotationAlpha,
        tg, rX, startAngle, finishAngle, start);

    // Sets the area to render
    rotator.setSchedulingBounds(_bounds);

    // Adds the rotation animation to the TransformGroup
    tg.addChild(rotator);
  }

  /**
   * Adds a PositionInterpolator Behavior to the SMGroup
   * 
   * @param duration
   * @param start
   * @param rotXAxis
   *          Angle about x in which the axis of translation is going to be
   *          rotated
   * @param rotYAxis
   *          Angle about x in which the axis of translation is going to be
   *          rotated
   * @param rotZAxis
   *          Angle about x in which the axis of translation is going to be
   *          rotated
   * @param startAngle
   * @param finsishAngle
   */
  public void addPositionAnim(long duration, long start, double rotXAxis,
      double rotYAxis, double rotZAxis, float startPos, float endPos) {
    // Creates a necessary TransformGroup for the SMPositionInterpolator
    TransformGroup tg = new TransformGroup();

    // Adds the animation between the child and it Parent
    Group parent = (Group) _child.getParent();
    parent.removeChild(_child);
    tg.addChild(_child);
    parent.addChild(tg);

    // Allows writing on TransfomGroup
    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

    // Creates an Alpha function
    Alpha rotationAlpha = new Alpha(1, duration);
    rotationAlpha.setStartTime(System.currentTimeMillis() + start);

    Transform3D rX = new Transform3D();
    Transform3D rY = new Transform3D();
    Transform3D rZ = new Transform3D();
    rX.rotX(rotXAxis);
    rY.rotY(rotYAxis);
    rZ.rotZ(rotZAxis);

    rY.mul(rZ);
    rX.mul(rY);

    // Creates the SMPositionInterpolator
    SMPositionInterpolator translator = new SMPositionInterpolator(
        rotationAlpha, tg, rX, startPos, endPos, start);

    translator.setSchedulingBounds(_bounds);

    tg.addChild(translator);
  }

}
