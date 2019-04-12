import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EnemyBulletTest
{
  @Test
  public void test_ConstructorWithJustCoordinates()
  {
    EnemyBullet eb = new EnemyBullet(0,0);
    assertEquals("An enemy bullet should have been created at x = 0.",0,eb.getXCord());
    assertEquals("An enemy bullet should have been created at y = 0.",0,eb.getYCord());
  }

  @Test
  public void test_ConstructorWithMomentum()
  {
    EnemyBullet eb = new EnemyBullet(0,0,0);
    assertEquals("An enemy bullet should have been created at x = 0.",0,eb.getXCord());
    assertEquals("An enemy bullet should have been created at y = 0.",0,eb.getYCord());
    assertEquals("An enemy bullet should have been created with 0 momentum.",0,eb.getMomentum());
  }

  @Test
  public void test_CopyConstructor()
  {
    EnemyBullet eb = new EnemyBullet(0,0,0);
    EnemyBullet enbu = new EnemyBullet(eb);
    assertEquals("The copy constructor should have created an enemy bullet at x = 0.",0,enbu.getXCord());
    assertEquals("The copy constructor should have created an enemy bullet at y = 0.",0,enbu.getYCord());
    assertEquals("The copy constructor should have created an enemy bullet with 1 momentum.",1,enbu.getMomentum());
  }

  @Test
  public void test_EnemyBulletMovement()
  {
    EnemyBullet eb = new EnemyBullet(0,0);
    assertTrue("Enemy bullet should have moved.", eb.move());
    assertEquals("Enemy bullet should have moved two spaces down.", 2, eb.getYCord());;
  }

  @Test
  public void test_MovesOutsideOfBoard()
  {
    EnemyBullet eb = new EnemyBullet(0,0);
    int y = eb.getMaxYCord()-1;
    eb.setYCord(y);
    assertFalse("Enemy bullet should have been removed from board.", eb.move());
    assertEquals("Enemy bullet went over board limit.", eb.getMaxYCord()-1, eb.getYCord());
  }
}
