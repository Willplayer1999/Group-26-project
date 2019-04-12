import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BulletTest
{
  @Test
  public void test_TextConstructor()
  {
    AvatarBullet ab = new AvatarBullet(1,1);
    EnemyBullet eb = new EnemyBullet(0,0);
    assertEquals("Avatar bullet should have been created at x = 1.",1,ab.getXCord());
    assertEquals("Avatar bullet should have been created at y = 1.",1,ab.getYCord());
    assertEquals("Enemy bullet should have been created at x = 0.",0,eb.getXCord());
    assertEquals("Enemy bullet should have been created at y = 0.",0,eb.getYCord());
    assertEquals("The avatars bullet momentum should be 2.",2,ab.getMomentum());
    assertEquals("The enemys bullet momentum should be 2.",2,eb.getMomentum());
    assertEquals("The avatars bullet max x coordinate should be the boards max x coordinate-1",TextGame.COLUMNS-1,ab.getMaxXCord());
    assertEquals("The avatars bullet max y coordinate should be the boards max y coordinate-1",TextGame.ROWS-1,ab.getMaxYCord());
    assertEquals("The enemys bullet max x coordinate should be the boards max x coordinate-1",TextGame.COLUMNS-1,eb.getMaxXCord());
    assertEquals("The enemys bullet max y coordinate should be the boards max y coordinate-1",TextGame.ROWS-1,eb.getMaxYCord());
    assertEquals("The avatars bullet height should be 0.",0,ab.getHeight());
    assertEquals("The enemys bullet height should be 0.",0,eb.getHeight());
    assertEquals("The avatars bullet width should be 0.",0,ab.getWidth());
    assertEquals("The enemys bullet width should be 0.",0,eb.getWidth());
  }

  @Test
  public void test_TextConstructor_WithNegativeCoordinates()
  {
    AvatarBullet ab = new AvatarBullet(-1,-1);
    EnemyBullet eb = new EnemyBullet(-2,-2);
    assertEquals("Avatar bullet should have been created at x = 0.",0,ab.getXCord());
    assertEquals("Avatar bullet should have been created at y = 0.",0,ab.getYCord());
    assertEquals("Enemy bullet should have been created at x = 0.",0,eb.getXCord());
    assertEquals("Enemy bullet should have been created at y = 0.",0,eb.getYCord());
  }

  @Test
  public void test_TextConstructor_WithBeyondBoundryCoordinates()
  {
    AvatarBullet ab = new AvatarBullet(TextGame.COLUMNS+1,TextGame.ROWS+1);
    EnemyBullet eb = new EnemyBullet(TextGame.COLUMNS+1,TextGame.ROWS+1);
    assertEquals("Avatar bullet should have been created at the maximum x coordinate.",ab.getMaxXCord(),ab.getXCord());
    assertEquals("Avatar bullet should have been created at the maximum y coordinate.",ab.getMaxYCord(),ab.getYCord());
    assertEquals("Enemy bullet should have been created at the maximum x coordinate.",eb.getMaxXCord(),eb.getXCord());
    assertEquals("Enemy bullet should have been created at the maximum y coordinate.",eb.getMaxYCord(),eb.getYCord());
  }

  @Test
  public void test_CopyConstructor_WithTextBullet()
  {
    AvatarBullet ab = new AvatarBullet(1,1);
    EnemyBullet eb = new EnemyBullet(0,0);
    AvatarBullet acb = new AvatarBullet(ab);
    EnemyBullet ecb = new EnemyBullet(eb);
    assertEquals("Avatar bullet should have been created at x = 1.",1,acb.getXCord());
    assertEquals("Avatar bullet should have been created at y = 1.",1,acb.getYCord());
    assertEquals("Enemy bullet should have been created at x = 0.",0,ecb.getXCord());
    assertEquals("Enemy bullet should have been created at y = 0.",0,ecb.getYCord());
    assertEquals("The avatars bullet momentum should be 1.",1,acb.getMomentum());
    assertEquals("The enemys bullet momentum should be 1.",1,ecb.getMomentum());
    assertEquals("The avatars bullet max x coordinate should be the boards max x coordinate-1",TextGame.COLUMNS-1,acb.getMaxXCord());
    assertEquals("The avatars bullet max y coordinate should be the boards max y coordinate-1",TextGame.ROWS-1,acb.getMaxYCord());
    assertEquals("The enemys bullet max x coordinate should be the boards max x coordinate-1",TextGame.COLUMNS-1,ecb.getMaxXCord());
    assertEquals("The enemys bullet max y coordinate should be the boards max y coordinate-1",TextGame.ROWS-1,ecb.getMaxYCord());
    assertEquals("The avatars bullet height should be 0.",0,acb.getHeight());
    assertEquals("The enemys bullet height should be 0.",0,ecb.getHeight());
    assertEquals("The avatars bullet width should be 0.",0,acb.getWidth());
    assertEquals("The enemys bullet width should be 0.",0,ecb.getWidth());
  }
}
