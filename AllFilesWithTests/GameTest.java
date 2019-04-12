import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class GameTest
{
  @Test
  public void test_MainConstructor()
  {
    TextGame tg = new TextGame();
    Avatar a = tg.getAvatar();
    int y = a.getY();
    assertEquals("Only avatar should have been created in the middle of the bottom row.", 9, y);
  }

  @Test
  public void test_SecondConstructor()
  {
    Avatar avatar = new Avatar(0,0);
    ArrayList<Platforms> platforms = new ArrayList<Platforms>(Arrays.asList(new Platforms(0,0,1,1)));
    ArrayList<Bullet> bullets = new ArrayList<Bullet>(Arrays.asList(new EnemyBullet(1,1)));
    ArrayList<Enemy> enemies = new ArrayList<Enemy>(Arrays.asList(new Enemy(2,2)));

    TextGame tg = new TextGame(platforms, bullets, enemies, avatar);
    Avatar a = tg.getAvatar();
    ArrayList<Platforms> plats = tg.getPlatforms();
    ArrayList<Bullet> bulls = tg.getBullets();
    ArrayList<Enemy> ens = tg.getEnemies();
    int y = a.getY();
    assertEquals("Only avatar should have been created in the first row.", 0, y);
    assertEquals("There should be one platfrom.",1, plats.size());
    assertEquals("There should be one bullet.",1,bulls.size());
    assertEquals("There should be one enemy.",1,ens.size());
  }

  @Test
  public void test_Collision_Avatar_and_EnemyBullet()
  {
    Avatar avatar = new Avatar(0,0);
    ArrayList<Enemy> enemies = new ArrayList<Enemy>(Arrays.asList(new Enemy(3,3)));
    ArrayList<Platforms> platforms = new ArrayList<Platforms>(Arrays.asList(new Platforms(0,0)));
    ArrayList<Bullet> bullets = new ArrayList<Bullet>(Arrays.asList(new EnemyBullet(0,0)));
    TextGame tg = new TextGame(platforms, bullets, enemies, avatar);
    tg.checkCollision();
    assertEquals("Avatar should have lost a live.",9,avatar.getLives());
  }

  @Test
  public void test_Collision_Enemy_and_AvatarBullet()
  {
    Avatar avatar = new Avatar(0,0);
    ArrayList<Enemy> enemies = new ArrayList<Enemy>(Arrays.asList(new Enemy(3,3)));
    ArrayList<Platforms> platforms = new ArrayList<Platforms>(Arrays.asList(new Platforms(0,0)));
    ArrayList<Bullet> bullets = new ArrayList<Bullet>(Arrays.asList(new AvatarBullet(3,3)));
    TextGame tg = new TextGame(platforms, bullets, enemies, avatar);
    tg.checkCollision();
    assertEquals("Enemy should have died.",0,enemies.size());
    assertEquals("Player should have gotten a point.",1,tg.getScore());
  }

  @Test
  public void test_text_Movement()
  {
    Avatar avatar = new Avatar(0,0);
    ArrayList<Platforms> platforms = new ArrayList<Platforms>(Arrays.asList(new Platforms(4,4)));
    ArrayList<Enemy> enemies = new ArrayList<Enemy>(Arrays.asList(new Enemy(3,3)));
    ArrayList<Bullet> bullets = new ArrayList<Bullet>(Arrays.asList(new AvatarBullet(5,5),new EnemyBullet(6,6)));
    TextGame tg = new TextGame(platforms, bullets, enemies, avatar);
    avatar.move("D");
    tg.move();
    tg.moveplatform();
    assertEquals("Enemy bullet should have moved 2 spaces down.",8,bullets.get(1).getYCord());
    assertEquals("Avatar bullet shoud have moved 2 spaces up.",3,bullets.get(0).getYCord());
    assertEquals("Platform should have moved to the right.",5,platforms.get(0).getX());
    assertEquals("Avatar should have moved one space to the right.",1,avatar.getX());
  }

  @Test
  public void test_EnemyHasNoShot()
  {
    Avatar avatar = new Avatar(0,0);
    Enemy enemy = new Enemy(0,0);
    enemy.setHasFired(false);
    ArrayList<Enemy> enemies = new ArrayList<Enemy>(Arrays.asList(new Enemy(enemy)));
    ArrayList<Platforms> platforms = new ArrayList<Platforms>(Arrays.asList(new Platforms(0,0)));
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    TextGame tg = new TextGame(platforms, bullets, enemies, avatar);
    tg.enemyShoot();
    assertEquals("Enemy should have no bullets.", 0, bullets.size());
  }
}
