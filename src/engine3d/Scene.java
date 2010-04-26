package engine3d;

import com.sun.j3d.utils.universe.*; 
import javax.media.j3d.*;
import javax.vecmath.*;


//this class loads all the scene in a simple universe
public class Scene {

	//Creates the scene in a simple universe
	public Scene(Canvas3D canvas3D) {

        BranchGroup scene = createSceneGraph();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

        simpleU.getViewingPlatform().setNominalViewingTransform();

        simpleU.addBranchGraph(scene);
	}

	//returns the scene
    public BranchGroup createSceneGraph() {

    	//creates the objects root
    	BranchGroup objRoot=new BranchGroup();
    	
    	//adds an arm
    	objRoot.addChild(new Arm());
    	
    	//adds a light, necessary to see the cylinders
    	objRoot.addChild(getLight());
    		
    	return objRoot;
    }

    //creates the light
    private DirectionalLight getLight() {
    	Color3f light_color = new Color3f(1.0f, 1.0f, 1.0f);
    	Vector3f light_direction = new Vector3f(8.0f, -7.0f, -12.0f);  //set the position depending on the objects position
    	DirectionalLight directional_light = new DirectionalLight(light_color, light_direction);
    	BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
    	directional_light.setInfluencingBounds(bounds);
    	
    	return directional_light;
    }     

	
}