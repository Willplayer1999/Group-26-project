import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlatformsTest
{
  @Test
  public void test_TextConstructor()
  {
    Platforms pl = new Platforms(0,0);
    assertEquals("Should have created a platform at x = 0.",0,pl.getX());
    assertEquals("Should have created a platform at y = 0.",0,pl.getY());
  }

  @Test
  public void test_CopyConstructor_WithTextParameters()
  {
    Platforms pl = new Platforms(0,0);
    Platforms cpl = new Platforms(pl);
    assertEquals("Should have created a platform at x = 0.",0,cpl.getX());
    assertEquals("Should have created a platform at y = 0.",0,cpl.getY());
  }
}
