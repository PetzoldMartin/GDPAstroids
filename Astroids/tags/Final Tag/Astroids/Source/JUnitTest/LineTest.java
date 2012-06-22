package JUnitTest;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Shapes.Line;
import Shapes.Point;

public class LineTest extends TestCase {
	Line l1;

	@Override
	@Before
	public void setUp() throws Exception {
		l1 = new Line(new Point(1, 1), new Point(2, 2));
	}

	@Override
	@After
	public void tearDown() throws Exception {
		l1 = null;
	}

	@Test
	public void testLinePointPoint() {
		assertTrue(new Line(new Point(1, 1), new Point(2, 2)).equals(l1));
		assertFalse(new Line(new Point(1, 1), new Point(2, 2)) == (l1));
		assertFalse(new Line(new Point(2, 1), new Point(2, 2)).equals(l1));
		assertFalse(new Line(new Point(1, 2), new Point(2, 2)).equals(l1));
		assertFalse(new Line(new Point(1, 1), new Point(1, 2)).equals(l1));
		assertFalse(new Line(new Point(1, 1), new Point(2, 1)).equals(l1));
		// TODO Test second Construktor of Line
	}
}
