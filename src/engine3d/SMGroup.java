package engine3d;

import javax.media.j3d.*;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector3d;

//This class is a Group that contains a group of objects and its transformations
public class SMGroup extends Group {

	private Group _group;
	private TransformGroup _rotation;
	private TransformGroup _translation;
	private TransformGroup _scale;	

	//constructor that creates a group with its transformations
	public SMGroup(Group group) {
		_group=group;
		_rotation=new TransformGroup();
		_translation=new TransformGroup();
		_scale=new TransformGroup();
		
		addChild(_translation);
		_translation.addChild(_rotation);
		_rotation.addChild(_scale);
		_scale.addChild(_group);
		
	}
	
	//set the translation parameters
	public void translation(float x, float y, float z) {
		Transform3D translate=new Transform3D();
		translate.setTranslation(new Vector3f(x,y,z));
		_translation.setTransform(translate);		
	}

	//set the rotation parameters
	public void rotation(float x, float y, float z) {
		Transform3D rotation=new Transform3D();
		rotation.rotX(x);
		rotation.rotY(y);
		rotation.rotZ(z);
		_rotation.setTransform(rotation);
	}

	//set the scale parameters
	public void scale(float x, float y, float z) {
		Transform3D scale=new Transform3D();
		scale.setScale(new Vector3d(x,y,z));
		_scale.setTransform(scale);
	}

}
