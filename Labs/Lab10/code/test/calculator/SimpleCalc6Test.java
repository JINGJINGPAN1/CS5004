package calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

class Controller6Test {

  @Test
  void testMultipleAdditions() throws IOException {
    String input = "+ 3 4 + 8 9 q";
    Readable in = new StringReader(input);
    Appendable out = new StringWriter();
    Controller6 controller = new Controller6(in, out);

    Calculator calc = new Calculator();
    controller.go(calc);

    assertEquals("7\n17\n", out.toString());
  }
}
