import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AvatarBulletTest
{
  @Test
  public void test_ConstructorWithJustCoordinates()
  {
    AvatarBullet ab = new AvatarBullet(0,0);
    assertEquals("An Avatar bullet should have been created at x = 0.",0,ab.getXCord());
    assertEquals("An Avatar bullet should have been created at y = 0.",0,ab.getYCord());
  }

  @Test
  public void test_ConstructorWithMomentum()
  {
    AvatarBullet ab = new AvatarBullet(0,0,0);
    assertEquals("An avatar bullet should have been created at x = 0.",0,ab.getXCord());
    assertEquals("An avatar bullet should have been created at y = 0.",0,ab.getYCord());
    assertEquals("An avatar bullet should have been created with 0 momentum.",0,ab.getMomentum());
  }

  @Test
  public void test_CopyConstructor()
  {
    AvatarBullet ab = new AvatarBullet(0,0,0);
    AvatarBullet enbu = new AvatarBullet(ab);
    assertEquals("The copy constructor should have created an avatar bullet at x = 0.",0,enbu.getXCord());
    assertEquals("The copy constructor should have created an avatar bullet at y = 0.",0,enbu.getYCord());
    assertEquals("The copy constructor should have created an avatar bullet with 1 momentum.",1,enbu.getMomentum());
  }

  @Test
  public void test_AvatarBulletMovement()
  {
    AvatarBullet ab = new AvatarBullet(3,3);
    assertTrue("Avatar bullet should have moved.", ab.move());
    assertEquals("Avatar bullet should have moved two spaces up.", 1, ab.getYCord());;
  }

  @Test
  public void test_MovesOutsideOfBoard()
  {
    AvatarBullet ab = new AvatarBullet(0,0);
    assertFalse("Avatar bullet should have been removed from board.", ab.move());
    assertEquals("Avatar bullet went over board limit.", 0, ab.getYCord());
  }
}
