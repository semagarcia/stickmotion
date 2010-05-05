package engine3d;

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

    // adds a light, necessary to see the cylinders
    objRoot.addChild(getLight());

    // creates a stickam (his name is steve :P)
    Stickman steve = new Stickman();

    // add steve as a child and zoom it, so that it can be displayed properly
    objRoot.addChild(steve);
    steve.scale(0.3, 0.3, 0.3);

    // A couple of animation examples

    // Going right
    steve.addRotationAnim(1000, 1000, 0, 0, 0, 0, (float) Math.PI / 2);
    steve.addPositionAnim(1000, 2000, 0, -Math.PI / 2, 0, 0, 1);
    // Going left
    steve.addRotationAnim(2000, 3000, 0, 0, 0, 0, (float) -Math.PI);
    steve.addPositionAnim(1000, 5000, 0, -Math.PI / 2, 0, 0, 1);
    // Look to the front
    steve.addRotationAnim(1000, 6000, 0, 0, 0, 0, (float) Math.PI / 2);

    // Crappy walking animation
    for (int time = 1000; time < 20000; time += 1000) {
      // First second
      // Move right Leg forward (z rot)
      steve.rLeg.addRotationAnim(1000, time, 0, 0, (float) Math.PI / 2, 0,
          (float) (Math.PI / 3));
      // Move right Leg backwards (z rot)
      steve.lLeg.addRotationAnim(1000, time, 0, 0, (float) Math.PI / 2, 0,
          (float) -(Math.PI / 3));
      // Flex foreLegs
      steve.rLeg.fore.addRotationAnim(1000, time, 0, 0, (float) Math.PI / 2, 0,
          (float) Math.PI / 3);
      steve.lLeg.fore.addRotationAnim(1000, time, 0, 0, (float) Math.PI / 2, 0,
          (float) Math.PI / 3);

      // Next second
      time += 1000;
      // Un-Flex foreLegs
      steve.rLeg.fore.addRotationAnim(1000, time, 0, 0, (float) Math.PI / 2, 0,
          (float) -Math.PI / 3);
      steve.lLeg.fore.addRotationAnim(1000, time, 0, 0, (float) Math.PI / 2, 0,
          (float) -Math.PI / 3);
      // Move right Leg backwards (z rot)
      steve.rLeg.addRotationAnim(1000, time, 0, 0, (float) Math.PI / 2, 0,
          (float) -(Math.PI / 3));
      // Move right Leg forward (z rot)
      steve.lLeg.addRotationAnim(1000, time, 0, 0, (float) Math.PI / 2, 0,
          (float) (Math.PI / 3));
    }

    // Try to fly
    for (int time = 1500; time < 20000; time += 1000) {
      // Raise both Arms
      steve.rArm.addRotationAnim(1000, time, (float) Math.PI / 2, 0, 0, 0,
          (float) (Math.PI / 3));
      steve.lArm.addRotationAnim(1000, time, (float) Math.PI / 2, 0, 0, 0,
          (float) (Math.PI / 3));
      // Flex forearms up
      steve.rArm.fore.addRotationAnim(1000, time, (float) Math.PI / 2, 0, 0, 0,
          (float) Math.PI / 3);
      steve.lArm.fore.addRotationAnim(1000, time, (float) Math.PI / 2, 0, 0, 0,
          (float) Math.PI / 3);

      // Next step
      time += 1000;
      // un-Flex forearms
      steve.rArm.fore.addRotationAnim(1000, time, (float) Math.PI / 2, 0, 0, 0,
          (float) -Math.PI / 3);
      steve.lArm.fore.addRotationAnim(1000, time, (float) Math.PI / 2, 0, 0, 0,
          (float) -Math.PI / 3);
      // Low both Arms
      steve.rArm.addRotationAnim(1000, time, (float) Math.PI / 2, 0, 0, 0,
          (float) -(Math.PI / 3));
      steve.lArm.addRotationAnim(1000, time, (float) Math.PI / 2, 0, 0, 0,
          (float) -(Math.PI / 3));
    }

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