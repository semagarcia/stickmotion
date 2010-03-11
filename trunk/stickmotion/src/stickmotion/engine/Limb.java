/**
 * 
 */
package stickmotion.engine;

/**
 * Clase de la que heredarán el resto de extremidades 
 *
 */
public class Limb {

	private double angle;
	private double size;
	
	/**
	 * @throws WrongSizeException 
	 * 
	 */
	public Limb(double angle, double size) throws WrongSizeException {
		setAngle(angle);
		setSize(size);
	}


	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}


	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @param size the size to set
	 * @throws WrongSizeException 
	 */
	public void setSize(double size) throws WrongSizeException {
		if(size > 1 || 0 < size) {
			throw new WrongSizeException();
		}
		this.size = size;
	}


	/**
	 * @return the size
	 */
	public double getSize() {
		return size;
	}
	
}
