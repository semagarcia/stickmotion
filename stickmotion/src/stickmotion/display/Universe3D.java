/**
 * Archivo Universe3D.java
 */
package stickmotion.display;

import java.net.MalformedURLException;
import java.net.URL;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 *
 */
public class Universe3D extends SimpleUniverse {

  /**
   * Constructor de la clase Universe3D
   * 
   * @param canvas3d
   */
  public Universe3D(Canvas3D canvas3d) {
    super(canvas3d);
  }

  /**
   * 
   * Método para cargar la escena y la navegación por teclado
   * 
   * @param su
   * @return
   */
  public void loadSceneGraph() {

    BranchGroup objRoot = new BranchGroup();

    objRoot.addChild(createLand());

    TransformGroup vpTrans = getViewingPlatform().getViewPlatformTransform();
    KeyNavigatorBehavior keyNavBeh = new Keybindings(vpTrans);
    objRoot.addChild(keyNavBeh);

    // Realiza optimizaciones en este grafo de escena que compone el entorno.
    objRoot.compile();

    // Añade la escena creada
    addBranchGraph(objRoot);
  }

  /**
   * 
   * Método para crear el terreno de cuadricula compuesto por lineas verdes.
   * 
   * @return Objeto 3D del terreno
   */
  private Shape3D createLand() {
    // Declara un array de lineas
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
   * Método para cargar un modelo 3D de formato obj
   * 
   * @param localización
   *          del archivo con el modelo 3D
   * @return objeto que contiene la escena del modelo
   */
  public BranchGroup loadModel() {
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
