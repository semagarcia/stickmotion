package engine3d;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

// this class loads all the scene in a simple universe
public class Scene {

  // Creates the scene in a simple universe
  public Scene(Canvas3D canvas3D) {

    BranchGroup scene = createSceneGraph();
    SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

    simpleU.getViewingPlatform().setNominalViewingTransform();

    simpleU.addBranchGraph(scene);
  }

  // returns the scene
  public BranchGroup createSceneGraph() {

    // creates the objects root
    BranchGroup objRoot = new BranchGroup();

    // creates a stickam (his name is steve :P)
    Stickman steve = new Stickman();

    // add steve as a child and zoom it, so that it can be displayed properly
    objRoot.addChild(steve);
    steve.scale(0.3, 0.3, 0.3);

    // adds a light, necessary to see the cylinders
    objRoot.addChild(getLight());

    return objRoot;
  }

  // creates the light
  private DirectionalLight getLight() {
    Color3f light_color = new Color3f(1.0f, 1.0f, 1.0f);
    Vector3f light_direction = new Vector3f(8.0f, -7.0f, -12.0f); // set the
    // position
    // depending
    // on the
    // objects
    // position
    DirectionalLight directional_light = new DirectionalLight(light_color,
        light_direction);
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
        100.0);
    directional_light.setInfluencingBounds(bounds);

    return directional_light;
  }

}