/**
 * This utility bridges between a PrintStream (eg System.out) and an Appendable (what our
 * output-generalized TextView needs)
 * <p></p>
 * The Constructor takes a PrintStream as input It implements the three required methods of the
 * Appendable interface. Needed because PrintStream does not implement Appendable (it should), but
 * Appendable is needed to support automated testing (I/O with strings) This adapter allows
 * continued use of System.out for text-based production version.
 */
import java.io.PrintStream;

public class PrintStreamAdapter implements Appendable {

  private final PrintStream ps;

  public PrintStreamAdapter(PrintStream ps) {
    this.ps = ps;
  }

  @Override
  public Appendable append(CharSequence csq) {
    ps.print(csq);
    return this;
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) {
    ps.print(csq.subSequence(start, end));
    return this;
  }

  @Override
  public Appendable append(char c) {
    ps.print(c);
    return this;
  }
}
