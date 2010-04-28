package engine3d;

import javax.media.j3d.Group;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cylinder;

// class for the stickman's arms
public class Arm extends Group {

  // size of the cylinders that emulates bones
  private final float HEIGHT = (float) 0.5;
  private final float RADIUS = (float) 0.05;

  // angle that anterior does with the forearm
  private final Vector3f _angFore;

  public Arm() {
    _angFore = new Vector3f(0, 0, (float) 0.5);

    // anterior doesn't have transformations, its transformations are done
    // directly in the arm
    Cylinder anterior = new Cylinder(RADIUS, HEIGHT);

    // fore is a SMGroup because of the transformations applied
    SMGroup fore = new SMGroup(new Cylinder(RADIUS, HEIGHT));

    // applies the rotation and it correction to the forearm
    fore.rotation(_angFore.x, _angFore.y, _angFore.z);
    fore.translation((float) (Math.sin(_angFore.z) * HEIGHT / 2), -HEIGHT,
        (float) (Math.sin(_angFore.x) * HEIGHT / 2));

    // adds the children to the arm
    addChild(fore);
    addChild(anterior);
  }

}
