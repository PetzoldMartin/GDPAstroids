package JUnitTest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import Astroids.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ShapeExceptions.PolygoneShapeException;
import Shapes.Point;

public class VectorTest {

	private Vector s1,s2,s3,s4,s5,x,y,z;

	@Before
	public void setUp() throws Exception {
		s1= new Vector();
		s2= new Vector(new Point(1, 1));
		s3= new Vector(new Point(1, -1));
		s4= new Vector(new Point(-1, -1));
		s5= new Vector(new Point(-1, 1));
		x= new Vector();
		y= new Vector();
		z= new Vector();
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testEqualsObject() {
		assertTrue("Die equals Methode testet nicht auf Reflexivität",
				x.equals(x));
		assertTrue("Die equals Methode testet nicht auf Symetrie", x.equals(y)
				&& y.equals(x));
		assertTrue("Die equals Methode testet nicht auf Transitivität",
				x.equals(y) && y.equals(z) && x.equals(z));
		z.changeVector(new Point(10,1));
		z.changeSpeed(19);
		assertFalse("Die equals Methode testet nicht auf Konsistenz",
				x.equals(z));
		assertFalse(x.equals(null));
	}

	@Test
	public void testVector() {
		assertEquals("Es wurde kein vector erstellt",new Vector(),s1);
	}

	@Test
	public void testVectorDoubleDouble() {
		assertEquals("Es wurde kein vector aus Ammount und Phi erstellt",new Vector(), new Vector(0,0));
	}

	@Test
	public void testVectorPoint() {
		assertEquals("Es wurde kein vector aus einem Punkt erstellt",new Vector(), new Vector(new Point(0,0)));
	}

	@Test
	public void testChangeVectorDoubleDouble() {
		s1.changeVector(1,1);
		assertEquals("der Vector wurde bei Positiven Werten mit Amount und Phi nicht wie erwartet verändert",new Vector(1,1), s1);
		s1.changeVector(-2,-2);
		assertEquals("der Vector wurde bei Negativen Werten mit Amount und Phi nicht wie erwartet verändert",new Vector(-1,-1), s1);
	}

	@Test
	public void testChangeVectorPoint() {
		s1.changeVector(new Point(1,1));
		assertEquals("der Vector wurde bei Positiven Werten um einen Punkt nicht wie erwartet verändert",new Vector(new Point(1,1)), s1);
		s1.changeVector(new Point(-2,-2));
		assertEquals("der Vector wurde bei Negativen Werten um einen Punkt nicht wie erwartet verändert",new Vector(new Point(-1,-1)), s1);
	}

	@Test
	public void testChangeDirection() {
		s1.changeDirection(1);
		assertEquals("der Vector wurde bei Positivem Drehsinn nicht wie erwartet verändert",new Vector(0,1), s1);
		s1.changeDirection(-2);
		assertEquals("der Vector wurde bei Negativem Drehsinn nicht wie erwartet verändert",new Vector(0,-1), s1);
	}

	@Test
	public void testChangeSpeed() {
		s1.changeSpeed(1);
		assertEquals("der Vector wurde bei Positiver Geschwindigkeit nicht wie erwartet verändert",new Vector(1,0), s1);
		s1.changeSpeed(-2);
		assertEquals("der Vector wurde bei Negativer Geschwindigkeit nicht wie erwartet verändert",new Vector(-1,0), s1);
	}

	@Test
	public void testGetAmount() {
		assertTrue("Amount im 1. Quadranten wurde falsch berechnet",s2.getAmount()==Math.sqrt(2));
		assertTrue("Amount im 4. Quadranten wurde falsch berechnet",s3.getAmount()==Math.sqrt(2));
		assertTrue("Amount im 3. Quadranten wurde falsch berechnet",s4.getAmount()==Math.sqrt(2));
		assertTrue("Amount im 2. Quadranten wurde falsch berechnet",s5.getAmount()==Math.sqrt(2));
		
	}

	@Test
	public void testGetPhi() {
		assertTrue("Amount im 1. Quadranten wurde falsch berechnet",s2.getPhi()==45);
		assertTrue("Amount im 3. Quadranten wurde falsch berechnet",s4.getPhi()==180+45);
		assertTrue("Amount im 4. Quadranten wurde falsch berechnet",s3.getPhi()==270+45);
		assertTrue("Amount im 2. Quadranten wurde falsch berechnet",s5.getPhi()==90+45);
	}

}
