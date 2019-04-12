import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AvatarTest
{
  @Test
  public void test_Constructor_WithXandY()
  {
    Avatar av = new Avatar(0,0);
    assertEquals("Should have created an avatar at x = 0.",0,av.getX());
    assertEquals("Should have created an avatar at y = 0.",0,av.getY());
  }

  @Test
  public void test_Constructor_WithX_Y_Radius_MaxXandMaxY()
  {
    Avatar av = new Avatar(0,0,0,0,0);
    assertEquals("Should have created an avatar at x = 0.",0,av.getX());
    assertEquals("Should have created an avatar at y = 0.",0,av.getY());
    assertEquals("Should have created an avatar with radius = 0.",0,av.getRadius());
    assertEquals("Should have created an avatar with MaxY = 0.",0,av.getMaxYCord());
    assertEquals("Should have created an avatar with MaxX = 0.",0,av.getMaxXCord());
  }

  @Test
  public void test_Constructor_WithX_YandRadius()
  {
    Avatar av = new Avatar(0,0,0);
    assertEquals("Should have created an avatar at x = 0.",0,av.getX());
    assertEquals("Should have created an avatar at y = 0.",0,av.getY());
    assertEquals("Should have created an avatar with radius = 0.",0,av.getRadius());
  }

  @Test
  public void test_CopyConstructor_WithXandY()
  {
    Avatar av = new Avatar(0,0);
    Avatar cav = new Avatar(av);
    assertEquals("Should have created an avatar at x = 0.",0,cav.getX());
    assertEquals("Should have created an avatar at y = 0.",0,cav.getY());
  }

  @Test
  public void test_CopyConstructor_WithX_Y_Radius_MaxXandMaxY()
  {
    Avatar av = new Avatar(0,0,0,0,0);
    Avatar cav = new Avatar(av);
    assertEquals("Should have created an avatar at x = 0.",0,cav.getX());
    assertEquals("Should have created an avatar at y = 0.",0,cav.getY());
    assertEquals("Should have created an avatar with radius = 0.",0,cav.getRadius());
    assertEquals("Should have created an avatar with MaxY = 0.",0,cav.getMaxYCord());
    assertEquals("Should have created an avatar with MaxX = 0.",0,cav.getMaxXCord());
  }

  @Test
  public void test_CopyConstructor_WithX_YandRadius()
  {
    Avatar av = new Avatar(0,0,0);
    Avatar cav = new Avatar(av);
    assertEquals("Should have created an avatar at x = 0.",0,cav.getX());
    assertEquals("Should have created an avatar at y = 0.",0,cav.getY());
    assertEquals("Should have created an avatar with radius = 0.",0,cav.getRadius());
  }

  @Test
  public void test_ScoreSetter()
  {
    Avatar av = new Avatar(0,0,0);
    av.setScore(6);
    assertEquals("Should have a score of 6.",6,av.getScore());
  }

  @Test
  public void test_LivesSetter()
  {
    Avatar av = new Avatar(0,0);
    av.setLives(1);
    assertEquals("Should have 1 life.",1,av.getLives());
  }
}
