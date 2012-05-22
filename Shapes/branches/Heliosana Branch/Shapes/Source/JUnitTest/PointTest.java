/**
 * 
 */
package JUnitTest;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Shapes.Point;

/**
 * @author Aismael
 * 
 */
public class PointTest extends TestCase {

	private Point x;
	private Point y;
	private Point z, u, center;

	@Override
	@Before
	public void setUp() throws Exception {
		x = new Point(1, 1);
		y = new Point(1, 1);
		z = new Point(1, 1);
		u = new Point(1000, 1000);
		center = new Point(0, 0);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Shapes.Point#Point(int, int)}.
	 */
	@Test
	public void testPoint() {
		fail("Not yet implemented");
		// TODO Test construktor of point
	}

	/**
	 * Test method for {@link Shapes.Point#move(int, int)}.
	 */
	@Test
	public void testMove() {
		assertTrue("Vortest vor move Fehlgeschlagen", x.equals(z));
		assertEquals("Die X Koordinate wird falsch �bergeben", z.getX(), 1, 0);
		assertEquals("Die Y Koordinate wird falsch �bergeben", z.getY(), 1, 0);
		z.move(new Point(10, 10));
		assertEquals("Die X Koordinate wird falsch verschoben", z.getX(), 11, 0);
		assertEquals("Die Y Koordinate wird falsch verschoben", z.getY(), 11, 0);
		assertEquals("der punkt wird falsch verschoben", z, new Point(11, 11));
		assertFalse("Die punkte sind nicht unabh�ngig", x.equals(z));

	}

	/**
	 * 
	 */
	@Test
	public void testequals() {
		assertTrue("Die equals Methode testet nicht auf Reflexivit�t",
				x.equals(x));
		assertTrue("Die equals Methode testet nicht auf Symetrie", x.equals(y)
				&& y.equals(x));
		assertTrue("Die equals Methode testet nicht auf Transitivit�t",
				x.equals(y) && y.equals(z) && x.equals(z));
		y = new Point(2, 2);
		assertFalse("Die equals Methode testet nicht auf Konsistenz",
				x.equals(y));
		assertFalse(x.equals(null));
	}

	/**
	 * 
	 */
	@Test
	public void rotate() {
		u.rotate(center, 45);
		assertEquals(u.getX(), 1414, 0.4);
		assertEquals(u.getY(), 0, 0.4);
		for (int i = 1; i <= 15; i++) {
			u.rotate(center, 15);
		}
		assertEquals(u.getX(), -1000, 0.4);
		assertEquals(u.getY(), 1000, 0.4);
	}

}
