package gh2;

/* Imports the required audio library from the
 * edu.princeton.cs.introcs package. */
import edu.princeton.cs.introcs.StdAudio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/** Tests the GuitarString class.
 *  @author Josh Hug
 */
public class TestGuitarString  {

  @Test
  public void testPluckTheAString() {
    GuitarString aString = new GuitarString(GuitarHeroLite.CONCERT_A);
    aString.pluck();
    for (int i = 0; i < 50000; i += 1) {
      StdAudio.play(aString.sample());
      aString.tic();
    }
  }

  @Test
  public void testSample() {
    GuitarString s = new GuitarString(100);
    assertEquals(0.0, s.sample(), 0.0);
    assertEquals(0.0, s.sample(), 0.0);
    assertEquals(0.0, s.sample(), 0.0);
    s.pluck();
    double sample = s.sample();
    assertNotEquals(0.0, sample, "After plucking, your samples should not be 0.");

    assertEquals(sample, s.sample(), 0.0, "Sample should not change the state of your string.");
    assertEquals(sample, s.sample(), 0.0, "Sample should not change the state of your string.");
  }


  @Test
  public void testTic() {
    GuitarString s = new GuitarString(100);
    assertEquals(0.0, s.sample(), 0.0);
    assertEquals(0.0, s.sample(), 0.0);
    assertEquals(0.0, s.sample(), 0.0);
    s.pluck();
    double sample1 = s.sample();
    assertNotEquals(0.0, sample1, "After plucking, your samples should not be 0.");

    s.tic();
    assertNotEquals(sample1, s.sample(), "After tic(), your samples should not stay the same.");
  }


  @Test
  public void testTicCalculations() {
    // Create a GuitarString of frequency 11025, which
    // is a Deque of length 4.
    GuitarString s = new GuitarString(11025);
    s.pluck();

    // Record the front four values, ticcing as we go.
    double s1 = s.sample();
    s.tic();
    double s2 = s.sample();
    s.tic();
    double s3 = s.sample();
    s.tic();
    double s4 = s.sample();

    // If we tic once more, it should be equal to 0.996*0.5*(s1 + s2)
    s.tic();

    double s5 = s.sample();
    double expected = 0.996 * 0.5 * (s1 + s2);

    // Check that new sample is correct, using tolerance of 0.001.
    // See JUnit documentation for a description of how tolerances work
    // for assertEquals(double, double)
    assertEquals(expected, s5, 0.001, "Wrong tic value. Try running the testTic method.");
  }
}

