package engine3d;

import javax.media.j3d.Alpha;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnElapsedTime;

/**
 * This class is an extension of the PositionInterpolator class thats wake up on
 * elapsed time indicated by start argument-waitTime seconds
 */
public class SMPositionInterpolator extends PositionInterpolator {
  private long _start;

  // Time of wait in order to prevent jumps of frames because of Alpha funcion
  private final long _waitTime = 500;

  /**
   * Constructor that creates the SMPositionInterpolator and sets the start time
   * 
   * @param rotationAlpha
   * @param tg
   * @param rX
   * @param startPos
   * @param endPos
   * @param start
   * 
   */
  public SMPositionInterpolator(Alpha rotationAlpha, TransformGroup tg,
      Transform3D rX, float startPos, float endPos, long start) {
    super(rotationAlpha, tg, rX, startPos, endPos);

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
