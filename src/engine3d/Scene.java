package engine3d;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * This class loads all the scene in a simple universe
 */
public class Scene {
  // Stickman
  private Stickman steve;
  // Time
  private long time;

  // Group containing the whole scene
  private BranchGroup sceneGroup;

  // 3D Universe that will load the Scene
  private SimpleUniverse simpleU;

  // Canvas where the universe will be drawn
  private final Canvas3D canvas3D;

  /**
   * Constructor for the class Scene
   */
  public Scene(Canvas3D canvas3D) {

    this.canvas3D = canvas3D;

    // Initializes the time counter
    setTime(1000);

    // Creates the scene and universe
    sceneGroup = createSceneGraph();

  }

  /**
   * Method for starting the scene
   * 
   * @arg canvas3D
   */
  public void start() {
    // Set the viewport for the universe
    simpleU.getViewingPlatform().setNominalViewingTransform();

    // Associate the scene to the universe to start the animation
    simpleU.addBranchGraph(sceneGroup);
  }

  /**
   * Clean the tree of animations
   */
  public void reset() {

    // Initializes the time counter
    setTime(1000);

    // Remove the previous universe and recreate a new one
    if (simpleU != null)
      simpleU.cleanup();
    simpleU = new SimpleUniverse(canvas3D);

    // Creates the scene and universe
    sceneGroup = createSceneGraph();
  }

  /**
   * Creates the scene and returns it
   */
  public BranchGroup createSceneGraph() {

    // creates the objects root
    BranchGroup objRoot = new BranchGroup();

    // adds a light, necessary to see the cylinders
    objRoot.addChild(getLight());

    // creates a stickam (his name is steve :P)
    steve = new Stickman();

    // add steve as a child and zoom it, so that it can be displayed properly
    objRoot.addChild(steve);
    steve.scale(0.3, 0.3, 0.3);

    return objRoot;
  }

  /**
   * Creates the light and returns it
   */
  private DirectionalLight getLight() {
    Color3f light_color = new Color3f(1.0f, 1.0f, 1.0f);
    Vector3f light_direction = new Vector3f(8.0f, -7.0f, -12.0f);
    // set the position depending on the objects position
    DirectionalLight directional_light = new DirectionalLight(light_color,
        light_direction);
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
        100.0);
    directional_light.setInfluencingBounds(bounds);

    return directional_light;
  }

  /**
   * Method for animating the rotation of a SMGroup in spherical coordinates
   * 
   * @param group
   *          SMgroup where the transformations are applied
   * @param azimuth
   *          angle of azimuth in spherical coordinates (rotation around the Z
   *          axis)
   * @param inclination
   *          angle of inclination in spherical coordinates (rotation around the
   *          X axis)
   * @param duration
   *          time for the animation to occur
   */
  public void rotateSMGroup(SMGroup group, float azimuth, float inclination,
      long duration) {
    group.addRotationAnim(duration, time, 0, 0, 0, 0, azimuth);
    group.addRotationAnim(duration, time, 0, 0, Math.PI / 2, 0, inclination);
  }

  /**
   * Method for rotating the head of the stickman
   * 
   * @param azimuth
   *          angle of azimuth in spherical coordinates (rotation around the Z
   *          axis)
   * @param inclination
   *          angle of inclination in spherical coordinates (rotation around the
   *          X axis)
   * @param duration
   *          time for the animation to occur
   */
  public void rotateHead(float azimuth, float inclination, long duration) {
    rotateSMGroup(steve.head, azimuth, inclination, duration);
  }

  /**
   * Method for rotating the right arm of the stickman
   * 
   * @param azimuth
   *          angle of azimuth in spherical coordinates (rotation around the Z
   *          axis)
   * @param inclination
   *          angle of inclination in spherical coordinates (rotation around the
   *          X axis)
   * @param duration
   *          time for the animation to occur
   */
  public void rotateRArm(float azimuth, float inclination, long duration) {
    rotateSMGroup(steve.rArm, azimuth, inclination, duration);
  }

  /**
   * Method for rotating the left arm of the stickman
   * 
   * @param azimuth
   *          angle of azimuth in spherical coordinates (rotation around the Z
   *          axis)
   * @param inclination
   *          angle of inclination in spherical coordinates (rotation around the
   *          X axis)
   * @param duration
   *          time for the animation to occur
   */
  public void rotateLArm(float azimuth, float inclination, long duration) {
    rotateSMGroup(steve.lArm, azimuth, inclination, duration);
  }

  /**
   * Method for rotating the right leg of the stickman
   * 
   * @param azimuth
   *          angle of azimuth in spherical coordinates (rotation around the Z
   *          axis)
   * @param inclination
   *          angle of inclination in spherical coordinates (rotation around the
   *          X axis)
   * @param duration
   *          time for the animation to occur
   */
  public void rotateRLeg(float azimuth, float inclination, long duration) {
    rotateSMGroup(steve.rLeg, azimuth, inclination, duration);
  }

  /**
   * Method for rotating the left leg of the stickman
   * 
   * @param azimuth
   *          angle of azimuth in spherical coordinates (rotation around the Z
   *          axis)
   * @param inclination
   *          angle of inclination in spherical coordinates (rotation around the
   *          X axis)
   * @param duration
   *          time for the animation to occur
   */
  public void rotateLLeg(float azimuth, float inclination, long duration) {
    rotateSMGroup(steve.lLeg, azimuth, inclination, duration);
  }

  /**
   * Method for flexing the right arm of the stickman
   * 
   * @param angle
   *          angle for the articulation to flex
   * @param duration
   *          time for the animation to occur
   */
  public void flexRArm(float angle, long duration) {
    rotateSMGroup(steve.rArm.fore, 0, angle, duration);
  }

  /**
   * Method for flexing the left arm of the stickman
   * 
   * @param angle
   *          angle for the articulation to flex
   * @param duration
   *          time for the animation to occur
   */
  public void flexLArm(float angle, long duration) {
    rotateSMGroup(steve.lArm.fore, 0, angle, duration);
  }

  /**
   * Method for flexing the right leg of the stickman
   * 
   * @param angle
   *          angle for the articulation to flex
   * @param duration
   *          time for the animation to occur
   */
  public void flexRLeg(float angle, long duration) {
    rotateSMGroup(steve.rLeg.fore, 0, angle, duration);
  }

  /**
   * Method for flexing the left leg of the stickman
   * 
   * @param angle
   *          angle for the articulation to flex
   * @param duration
   *          time for the animation to occur
   */
  public void flexLLeg(float angle, long duration) {
    rotateSMGroup(steve.lLeg.fore, 0, angle, duration);
  }

  /**
   * Method for rotating the stickman
   * 
   * @param azimuth
   *          angle of azimuth in spherical coordinates (rotation around the Z
   *          axis)
   * @param inclination
   *          angle of inclination in spherical coordinates (rotation around the
   *          X axis)
   * @param duration
   *          time for the animation to occur
   */
  public void rotateStickman(float azimuth, float inclination, long duration) {
    rotateSMGroup(steve, azimuth, inclination, duration);
  }

  public void moveForwardStickman(float distance, long duration) {
    steve.addPositionAnim(duration, time, 0, -Math.PI / 2, 0, 0, distance);
  }

  /**
   * Method for incrementing the timer
   * 
   * @param step
   *          number of miliseconds to increment the time
   */
  public void addTime(long step) {
    time += step;
  }

  /**
   * Method for getting the value of the time
   * 
   * @return returns time value
   */
  public long getTime() {
    return time;
  }

  /**
   * Method for setting the value of the time
   * 
   * @param time
   *          Value of time to set
   */
  public void setTime(long time) {
    this.time = time;
  }

}