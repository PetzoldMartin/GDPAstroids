package JUnitTest;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Astroids.SpaceShip;
import Astroids.Sprite;
import Shapes.Point;

public class SpriteTest extends TestCase {
	Sprite test;

	@Before
	public void setUp() throws Exception {
		test = new SpaceShip();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSprite() {
		test.move(new Point(0, 100));
		System.out.println(test.getMiddlePoint().getX()+"  "+(test.getMiddlePoint().getY()));
		assertEquals(test.getMiddlePoint(),new Point(0, 100));
		test.rotate(45);
		System.out.println(test.getMiddlePoint().getX()+"  "+(test.getMiddlePoint().getY()));
		assertEquals(test.getMiddlePoint(),new Point(0, 100));
		fail("Not yet implemented");
	}

	@Test
	public void testSpritePoint() {
		fail("Not yet implemented");
		//TODO SpritePoint Test
	}

	@Test
	public void testSpritePointVector() {
		fail("Not yet implemented");
		//TODO SpritePointVector Test
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
		//TODO SpriteUpdate Test
	}

	@Test
	public void testMove() {
		fail("Not yet implemented");
		//TODO SpriteMove Test
	}

	@Test
	public void testRotateDouble() {
		fail("Not yet implemented");
		//TODO SpriteRotate Test
	}

	@Test
	public void testGetMiddlePoint() {
		fail("Not yet implemented");
		//TODO SpriteGetMiddlePoint Test
	}

	@Test
	public void testSetMiddlePoint() {
		fail("Not yet implemented");
		//TODO SetMiddlePoint Test
	}

	@Test
	public void testDraw() {
		fail("Not yet implemented");
		//TODO SpriteDraw Test
	}

	@Test
	public void testMovePoint() {
		fail("Not yet implemented");
		//TODO SpriteMovePoint Test
	}

	@Test
	public void testRotatePointDouble() {
		fail("Not yet implemented");
		//TODO SpriteRotatePoint Test
	}

	@Test
	public void testAddShape() {
		fail("Not yet implemented");
		//TODO SpriteAddShape Test
	}

}
