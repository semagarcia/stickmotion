package gui;

/**
 * Thread for interpreting the code and loading the animation in the StickMotion
 * interface
 */
public class InterpreterThread extends Thread {

  private final String _code;
  private final int _debugMode;
  private String _results;
  private final StickMotion _sm;

  /**
   * Constructor for the class InterpreterThread
   * 
   * @param sm
   *          Stickmotion Window interface containing the scene and output box
   * @param text
   *          Code to be interpreted for generating the animation
   * @param dM
   *          Debug level for showing debug messages
   */
  InterpreterThread(StickMotion sm, String text, int dM) {
    super();
    _code = text;
    _debugMode = dM;
    _sm = sm;
  }

  /**
   * Method to run when the thread is started
   */
  @Override
  public void run() {
    _results = sticky.Processor.run(_code, _debugMode);
    _sm.setResults(_results);
    StickMotion.scene.start();
    _sm.enablePlay();
  }
}
