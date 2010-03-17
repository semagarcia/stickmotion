/**
 * Archivo Frame3D.java
 */
package stickmotion.display;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 *
 */
@SuppressWarnings("serial")
public class Frame3D extends Frame {

  /**
   * 
   * Constructor de la clase Frame3D
   * 
   */
  public Frame3D() {
    super();
    this.setSize(800, 600);
    setLayout(new BorderLayout());
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

    Canvas3D canvas3D = new Canvas3D(config);
    this.add("Center", canvas3D);

    // SimpleUniverse is a Convenience Utility class
    SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

    BranchGroup scene = createSceneGraph(simpleU);

    simpleU.addBranchGraph(scene);
    simpleU.addBranchGraph(loadModel());
  }

  /**
   * 
   * Método para crear el terreno
   * 
   * @return
   */
  Shape3D createLand() {
    LineArray landGeom = new LineArray(44, GeometryArray.COORDINATES
        | GeometryArray.COLOR_3);
    float l = -50.0f;
    for (int c = 0; c < 44; c += 4) {

      landGeom.setCoordinate(c + 0, new Point3f(-50.0f, 0.0f, l));
      landGeom.setCoordinate(c + 1, new Point3f(50.0f, 0.0f, l));
      landGeom.setCoordinate(c + 2, new Point3f(l, 0.0f, -50.0f));
      landGeom.setCoordinate(c + 3, new Point3f(l, 0.0f, 50.0f));
      l += 10.0f;
    }

    Color3f c = new Color3f(0.1f, 0.8f, 0.1f);
    for (int i = 0; i < 44; i++)
      landGeom.setColor(i, c);

    return new Shape3D(landGeom);
  }

  /**
   * 
   * Método para cargar la escena y la navegación por teclado
   * 
   * @param su
   * @return
   */
  public BranchGroup createSceneGraph(SimpleUniverse su) {

    // Create the root of the branch graph
    TransformGroup vpTrans = null;

    BranchGroup objRoot = new BranchGroup();

    Vector3f translate = new Vector3f();
    Transform3D T3D = new Transform3D();
    TransformGroup TG = null;

    objRoot.addChild(createLand());

    float[][] position = { { 0.0f, 0.0f, -3.0f }, { 6.0f, 0.0f, 0.0f },
        { 6.0f, 0.0f, 6.0f }, { 3.0f, 0.0f, -10.0f }, { 13.0f, 0.0f, -30.0f },
        { -13.0f, 0.0f, 30.0f }, { -13.0f, 0.0f, 23.0f }, { 13.0f, 0.0f, 3.0f } };

    for (int i = 0; i < position.length; i++) {
      translate.set(position[i]);
      T3D.setTranslation(translate);
      TG = new TransformGroup(T3D);
      // TG.addChild(new Link(new SharedGroup));
      objRoot.addChild(TG);
    }

    vpTrans = su.getViewingPlatform().getViewPlatformTransform();
    KeyNavigatorBehavior keyNavBeh = new Keybindings(vpTrans);
    objRoot.addChild(keyNavBeh);

    // Let Java 3D perform optimizations on this scene graph.
    objRoot.compile();

    return objRoot;
  } // end of CreateSceneGraph method of KeyNavigatorApp

  /**
   * 
   * Método para cargar un modelo 3D
   * 
   * @param localización
   *          del archivo con el modelo 3D
   * @return objeto que contiene la escena del modelo
   */
  private BranchGroup loadModel() {
    ObjectFile obj = new ObjectFile();
    Scene scene = null;
    URL fname = null;
    try {
      fname = new URL("file:models/ducky.obj");
    } catch (MalformedURLException e1) {
      e1.printStackTrace();
    }
    try {
      scene = obj.load(fname);
    } catch (Exception e) {
      System.out.println("Error al cargar el modelo 3D (" + fname + ")");
      e.printStackTrace();
    }
    return scene.getSceneGroup();
  }

}
