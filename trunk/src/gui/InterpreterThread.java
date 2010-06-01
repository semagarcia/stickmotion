package gui;

/**
 *
 */
public class InterpreterThread extends Thread {

  private String _code;
  private int _debugMode;
  private String _results;
  private StickMotion _sm;

  InterpreterThread() {
    super();
  }

  public void interpreter(StickMotion sm, String text, int dM) {
    _code = text;
    _debugMode = dM;
    _sm = sm;
    run();
  }

  @Override
  public void run() {
    _results = sticky.Processor.run(_code, _debugMode);
    _sm.setResults(_results);
    _sm.enablePlay();
  }
}
