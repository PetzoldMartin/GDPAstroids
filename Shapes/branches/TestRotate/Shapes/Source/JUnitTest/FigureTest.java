package JUnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Shapes.Figure;
import Shapes.Point;

public class FigureTest {
	Figure test;

	@Before
	public void setUp() throws Exception {
		 test = Figure.ship();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		test.move(new Point(0, 100));
		System.out.println(test.getMiddle().getX()+"  "+(test.getMiddle().getY()));
		assertEquals(test.getMiddle(),new Point(0, 110));
		test.rotate(45);
		System.out.println(test.getMiddle().getX()+"  "+(test.getMiddle().getY()));
		assertEquals(test.getMiddle(),new Point(0, 110));

		
	}

}
