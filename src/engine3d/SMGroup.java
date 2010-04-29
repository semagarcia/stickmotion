package engine3d;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * This class is a Group that contains a group of objects and its
 * transformations
 */
public class SMGroup extends Group {

  private final TransformGroup _rotation;
  private final TransformGroup _translation;
  private final TransformGroup _scale;

  // constructor that creates a group with its transformations
  public SMGroup() {
    _rotation = new TransformGroup();
    _translation = new TransformGroup();
    _scale = new TransformGroup();

    addChild(_translation);
    _translation.addChild(_rotation);
    _rotation.addChild(_scale);

  }

  /**
   * Method for adding a child to the tree so that transformations can be
   * performed over it.
   * 
   * @param group
   */
  public void addSMChild(Group group) {
    _scale.addChild(group);
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
  public void scale(float x, float y, float z) {
    Transform3D scale = new Transform3D();
    scale.setScale(new Vector3d(x, y, z));
    _scale.setTransform(scale);
  }

}
