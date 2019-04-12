import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EnemyTest
{
  @Test
  public void test_Constructor_WithXandY()
  {
    Enemy en = new Enemy(0,0);
    assertEquals("Should have created an enemy at x = 0.",0,en.getX());
    assertEquals("Should have created an enemy at y = 0.",0,en.getY());
  }

  @Test
  public void test_CopyConstructor_WithXandY()
  {
    Enemy en = new Enemy(0,0);
    Enemy cen = new Enemy(en);
    assertEquals("Should have created an enemy at x = 0.",0,cen.getX());
    assertEquals("Should have created an enemy at y = 0.",0,cen.getY());
  }

  @Test
  public void test_HealthSetter()
  {
    Enemy en = new Enemy(0,0);
    en.setEnemyHealth(0);
    assertEquals("Enemy should have 0 health.",0,en.getHealth());
  }
}
