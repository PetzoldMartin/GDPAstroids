package JUnitTest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ShapeExceptions.PolygoneShapeException;
import Shapes.Point;
import Shapes.Polygon;

public class PolygonTest extends TestCase{
	Polygon x, y, z;
	ArrayList<Point> A1,A2,A3;

	@Before
	public void setUp() throws Exception {
		x = new Polygon();
		A1 = new ArrayList<Point>();
		A1.add(new Point(20, -35));
		A1.add(new Point(20, -45));
		A1.add(new Point(40, -30));
		A1.add(new Point(40, -20));
		x.setPoints(A1);
		x.setSolid(true);
		x.setColor(Color.RED);

		y = new Polygon();
		A2 = new ArrayList<Point>();
		A2.add(new Point(20, -35));
		A2.add(new Point(20, -45));
		A2.add(new Point(40, -30));
		A2.add(new Point(40, -20));
		y.setPoints(A2);
		y.setSolid(true);
		y.setColor(Color.RED);

		z = new Polygon();
		A3 = new ArrayList<Point>();
		A3.add(new Point(20, -35));
		A3.add(new Point(20, -45));
		A3.add(new Point(40, -30));
		A3.add(new Point(40, -20));
		z.setPoints(A3);
		z.setSolid(true);
		z.setColor(Color.RED);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDraw() {
		//TODO implement Polygon testDraw
		fail("Not yet implemented");
	}

	@Test
	public void testPolygon() {
		//TODO implement Polygon testPolygon
		fail("Not yet implemented");
	}

	@Test
	public void testGetPoints() {
		//TODO implement Polygon testGetPoints
		fail("Not yet implemented");
	}

	@Test
	public void testMove() {
		assertTrue("Vortest vor move Fehlgeschlagen",x.equals(z));
		assertEquals("Das Punkte Arrey wird falsch übergeben",z.getPoints(), A3);
		z.move(new Point (10,10));
		ArrayList<Point> A4 =A3;
		for(Iterator <Point> iterator = A4.iterator(); iterator.hasNext();)
        {
            iterator.next().move(new Point (10,10));
        }
		assertEquals("Ein Punkt wurde falsch verschoben",z.getPoints(), A4);
		assertFalse("Die Polygone sind nicht unabhängig",x.equals(z));
		
	}

	@Test
	public void testSetPoints() {
		//TODO implement Polygon testSetPoints
		fail("Not yet implemented");
	}

	

	@Test
	public void testEqualsPolygon() {
		assertTrue("Die equals Methode testet nicht auf Reflexivität", x.equals(x));
		assertTrue("Die equals Methode testet nicht auf Symetrie",x.equals(y)&&y.equals(x));
	    assertTrue("Die equals Methode testet nicht auf Transitivität",x.equals(y)&&y.equals(z)&&x.equals(z));
	    ArrayList<Point> A4 = new ArrayList<Point>();
		A4.add(new Point(0, 0));
		try {
			z.setPoints(A4);
		} catch (PolygoneShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		z.setSolid(true);
		z.setColor(Color.BLUE);
		assertFalse("Die equals Methode testet nicht auf Konsistenz",x.equals(z));
		assertFalse(x.equals(null));
		System.out.println(z.getPoints().size());
		
	}

}
