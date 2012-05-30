/**
 * 
 */
package JUnitTest;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Shapes.Circle;
import Shapes.Point;

/**
 * @author Aismael
 * 
 */
public class CircleTest extends TestCase {
	// TODO CircleTest
	Circle x, y, z;

	@Override
	@Before
	public void setUp() throws Exception {
		x = new Circle(1, new Point(1, 1));
		y = new Circle(1, new Point(1, 1));
		z = new Circle(1, new Point(1, 1));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Shapes.Circle#move(int, int)}.
	 */
	@Test
	public void testMove() {
		assertTrue("Vortest vor move Fehlgeschlagen", x.equals(z));
		assertEquals("der Kreismittelpunkt wird falsch �bergeben",
				z.getCenter(), new Point(1, 1));
		assertEquals("der Kreisradius wird falsch �bergeben", z.getRadius(), 1,
				0);
		z.move(new Point(10, 10));
		assertEquals("der Kreismittelpunkt wird falsch verschoben",
				z.getCenter(), new Point(11, 11));
		assertEquals("der Kreisradius ist inkonsistent", z.getRadius(), 1, 0);
		assertFalse("Die kreismittelpunkte sind nicht unabh�ngig", x.equals(z));

	}

	/**
	 * Test method for {@link Shapes.Circle#Circle(int, Shapes.Point)}.
	 */
	@Test
	public void testCircle() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Shapes.Shape#getWhiteBoard()}.
	 */
	@Test
	public void testGetWhiteBoard() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Shapes.Shape#setColor(java.awt.Color)}.
	 */
	@Test
	public void testSetColor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Shapes.Shape#setSolid(boolean)}.
	 */
	@Test
	public void testSetSolid() {
		fail("Not yet implemented");
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
		y = new Circle(2, new Point(2, 2));
		assertFalse("Die equals Methode testet nicht auf Konsistenz",
				x.equals(y));
		assertFalse(x.equals(null));
	}

}
