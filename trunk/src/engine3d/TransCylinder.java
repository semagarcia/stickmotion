/**
 * File TransCylinder.java
 */
package engine3d;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cylinder;

/**
 * This class will create a group containing a cylinder that's transposed in
 * such a way that the center or coordinates of the group (0,0,0) is one of the
 * ends of the cylinder (the other end will be at (0,-HEIGHT,0)).
 */
public class TransCylinder extends TransformGroup {

  /**
   * 
   * Constructor for the class TransCylinder
   * 
   * @param radius
   *          Radius of the cylinder to create
   * @param height
   *          Height of the cylinder to create
   */
  public TransCylinder(float radius, float height) {

    // create the cylinder and add it as child of this transformation group
    Cylinder cylinder = new Cylinder(radius, height);
    addChild(cylinder);

    // apply a translation to the cylinder so that it's below the center of
    // coordinates
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3f(0, -height / 2, 0));
    setTransform(translate);
  }

}
