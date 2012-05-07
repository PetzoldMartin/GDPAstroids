package JUnitTest;

import static org.junit.Assert.*;
import Shapes.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PointTest {

	Point testA, testB, testC;

	@Before
	public void setUp() throws Exception {
		testA = new Point(1, 1);
		testB = new Point(2, 2);
		testC = new Point(1, 1);
	}

	@After
	public void tearDown() throws Exception {
		testA = null;
		testB = null;
		testC = null;
	}

	@Test
	public void testPoint() {
		assertFalse(testA.equals(testB));
		assertEquals(testA, testA);
		assertTrue(testB.equals(testB));
	}

	@Test
	public void testMove() {
		System.out.println(testA.getX() + testB.getX());
		assertTrue(testA.move(new Point(0, 0)).equals(testA));
		assertTrue(testA.move(new Point(0, 0)).equals(testC));
		double a = testA.getX() + testB.getX();
		double b = testA.getY() + testB.getY();
		assertTrue(testA.move(testB).equals(new Point(a, b)));
		assertTrue((new Point(testA.getX() + testB.getX(), testA.getY()
				+ testB.getY())).equals(testA.move(testB)));
	}

}