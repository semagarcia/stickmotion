/**
 * Archivo Universe3D.java
 */
package stickmotion.display;

import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.LineArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.image.TextureLoader;
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
    objRoot.addChild(createBackground());

    TransformGroup vpTrans = getViewingPlatform().getViewPlatformTransform();
    KeyNavigatorBehavior keyNavBeh = new Keybindings(vpTrans);
    objRoot.addChild(keyNavBeh);

    objRoot.addChild(createText("<-- Stickmotion 3D -->"));

    // Realiza optimizaciones en este grafo de escena que compone el entorno.
    objRoot.compile();

    // Añade la escena creada
    addBranchGraph(objRoot);
  }

  /**
   * 
   * Método para crear el fondo de pantalla
   * 
   * @return
   * 
   */
  public Background createBackground() {

    // Esfera circundante sobre la que se dibujará el fondo
    BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0, 0.0,
        0.0), 100.0);
    // Carga la textura para el fondo
    TextureLoader backgroundTexture = new TextureLoader("images/bg.jpg",
        getCanvas());

    // Background background = new Background(backgroundTexture.getImage());
    Background background = new Background();
    background.setColor(new Color3f(0.0f, 0.10f, 0.75f));
    background.setApplicationBounds(boundingSphere);

    return background;
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
   * Método para añadir texto en 3D al entorno
   * 
   * @param str
   * @return
   */
  public Shape3D createText(String str) {

    Appearance textAppear = new Appearance();
    ColoringAttributes textColor = new ColoringAttributes();
    textColor.setColor(1.0f, 0.0f, 0.0f);
    textAppear.setColoringAttributes(textColor);
    textAppear.setMaterial(new Material());

    // Create a simple shape leaf node, add it to the scene graph.
    Font3D font3D = new Font3D(new Font("Helvetica", Font.PLAIN, 1),
        new FontExtrusion());
    Text3D textGeom = new Text3D(font3D, new String(str));
    textGeom.setAlignment(Text3D.ALIGN_CENTER);
    Shape3D textShape = new Shape3D();
    textShape.setGeometry(textGeom);
    textShape.setAppearance(textAppear);

    return textShape;
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
