package JUnitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Shapes.Point;
import Shapes.Rectangle;

public class RectangleTest extends TestCase {
	Rectangle x,y,z;
	ArrayList<Point> A1;
	@Before
	public void setUp() throws Exception {
		x=new Rectangle(new Point(1, 1), 1, 1);
		y=new Rectangle(new Point(1, 1), 1, 1);
		z=new Rectangle(new Point(1, 1), 1, 1);
		A1 = new ArrayList<Point>();
		A1.add(new Point(1,1));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue("Die equals Methode testet nicht auf Reflexivität",x.equals(x));
		assertTrue("Die equals Methode testet nicht auf Symetrie",x.equals(y)&&y.equals(x));
	}
	@Test
	public void testMove() {
		//TODO Test move in Rectangle Test implementieren
		
	}

}
