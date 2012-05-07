package JUnitTest;

import static org.junit.Assert.*;
import Shapes.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CircleTest {

	Circle c1;

	@Before
	public void setUp() throws Exception {
		c1 = new Circle(10, new Point(10, 10));
	}

	@After
	public void tearDown() {
		c1 = null;
	}

	@Test
	public void testDraw() {
		try {
			c1.draw();
		} catch (Exception e) {
			assertTrue(false);
		}

	}

	@Test
	public void testMove() {
		c1.move(new Point(10, 10));
		assertTrue(c1.getCenter().equals(new Point(20, 20)));
		c1.move(new Point(-20, -20));
		assertTrue(c1.getCenter().equals(new Point(0, 0)));

	}

	@Test
	public void testCircleIntPoint() {
		assertTrue(new Circle(10, new Point(10, 10)).equals(c1));
		assertFalse(new Circle(10, new Point(10, 10)) == (c1));
		assertFalse(new Circle(11, new Point(10, 10)).equals(c1));
		assertFalse(new Circle(10, new Point(11, 10)).equals(c1));
		assertFalse(new Circle(10, new Point(10, 11)).equals(c1));
	}
}
