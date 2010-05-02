package engine3d;

import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnElapsedTime;

/**
 * This class is an extension of the RotationInterpolator class thats wake up on
 * elapsed time indicated by start argument-0.5 seconds
 */
public class SMRotationInterpolator extends RotationInterpolator {
  private long _start;

  // Time of wait in order to prevent jumps of frames because of Alpha funcion
  private final long _waitTime = 500;

  /**
   * Constructor that creates the SMRotationInterpolator and sets the start time
   * 
   * @param rotationAlpha
   * @param tg
   * @param rX
   * @param startAngle
   * @param finishAngle
   * @param start
   * 
   */
  public SMRotationInterpolator(Alpha rotationAlpha, TransformGroup tg,
      Transform3D rX, float startAngle, float finishAngle, long start) {
    super(rotationAlpha, tg, rX, startAngle, finishAngle);

    _start = start - _waitTime;

    if (start < 0)
      _start = 0; // _start can't be less than 0
  }

  /**
   * Sets the wake up criterion
   * 
   */
  @Override
  public void initialize() {
    super.wakeupOn(new WakeupOnElapsedTime(_start));
  }
}
