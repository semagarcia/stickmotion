package engine3d;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

// This class is a Group that contains a group of objects and its
// transformations
public class SMGroup extends Group {

  private final Group _group;
  private final TransformGroup _rotation;
  private final TransformGroup _translation;
  private final TransformGroup _scale;

  // constructor that creates a group with its transformations
  public SMGroup(Group group) {
    _group = group;
    _rotation = new TransformGroup();
    _translation = new TransformGroup();
    _scale = new TransformGroup();

    addChild(_translation);
    _translation.addChild(_rotation);
    _rotation.addChild(_scale);
    _scale.addChild(_group);

  }

  // set the translation parameters
  public void translation(float x, float y, float z) {
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3f(x, y, z));
    _translation.setTransform(translate);
  }

  // set the rotation parameters
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

  // set the scale parameters
  public void scale(float x, float y, float z) {
    Transform3D scale = new Transform3D();
    scale.setScale(new Vector3d(x, y, z));
    _scale.setTransform(scale);
  }

}
