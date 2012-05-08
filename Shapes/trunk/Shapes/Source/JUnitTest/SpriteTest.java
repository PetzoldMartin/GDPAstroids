package JUnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Shapes.Figure;
import Shapes.Point;
import Shapes.Sprite;

public class SpriteTest {
	Sprite test;

	@Before
	public void setUp() throws Exception {
		 test = Sprite.ship();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		test.move(new Point(0, 100));
		System.out.println(test.getMiddlePoint().getX()+"  "+(test.getMiddlePoint().getY()));
		assertEquals(test.getMiddlePoint(),new Point(0, 100));
		test.rotate(45);
		System.out.println(test.getMiddlePoint().getX()+"  "+(test.getMiddlePoint().getY()));
		assertEquals(test.getMiddlePoint(),new Point(0, 100));

		
	}

}