package JUnitTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Collision.CollisionDetector;
import Shapes.Circle;
import Shapes.Line;
import Shapes.Point;

/** @author Frank Grimm, Mai 2012 */
public class CollisionDetectorTests {
	private CollisionDetector collisionDetector;

	@Before
	public void setUp() {
		collisionDetector = new CollisionDetector();
	}

	@Test
	public void linesCollideTests() {
		{
			Line l1 = new Line(new Point(1, 1), new Point(3, 3));
			Line l2 = new Line(new Point(1, 3), new Point(3, 1));

			Assert.assertTrue(collisionDetector.collide(l1, l2));
		}

		{
			// special case (identical lines)
			Line l1 = new Line(new Point(1, 1), new Point(3, 3));
			Line l2 = new Line(new Point(1, 1), new Point(3, 3));

			Assert.assertTrue(collisionDetector.collide(l1, l2));
		}

		{
			// special case (line l2 is a point)
			Line l1 = new Line(new Point(1, 1), new Point(3, 3));
			Line l2 = new Line(new Point(2, 2), new Point(2, 2));

			Assert.assertTrue(collisionDetector.collide(l1, l2));
		}

		{
			// special case (lines are both points and coincidental
			// (overlapping))
			Line l1 = new Line(new Point(1, 1), new Point(1, 1));
			Line l2 = new Line(new Point(1, 1), new Point(1, 1));

			Assert.assertTrue(collisionDetector.collide(l1, l2));
		}

		{
			// special case (parallel lines)
			Line l1 = new Line(new Point(1, 1), new Point(3, 3));
			Line l2 = new Line(new Point(1, 1.1), new Point(3, 3.1));

			Assert.assertFalse(collisionDetector.collide(l1, l2));
		}

		{
			// special case (coincidental (overlapping) lines)
			Line l1 = new Line(new Point(1, 1), new Point(3, 3));
			Line l2 = new Line(new Point(0, 0), new Point(4, 4));

			Assert.assertTrue(collisionDetector.collide(l1, l2));
		}

		{
			Line l1 = new Line(new Point(1, 1), new Point(3, 3));
			Line l2 = new Line(new Point(3.01, 3), new Point(4, 4));

			Assert.assertFalse(collisionDetector.collide(l1, l2));
		}
	}

	@Test
	public void lineCollidesWithCircleTests() {
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(1, -1), new Point(1, 1));

			Assert.assertTrue(collisionDetector.collide(l, c));
		}
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(1.1, -1), new Point(1.1, 1));

			Assert.assertFalse(collisionDetector.collide(l, c));
		}
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(0, -1), new Point(0, 1));

			Assert.assertTrue(collisionDetector.collide(l, c));
		}
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(-1, -1), new Point(-1, 1));

			Assert.assertTrue(collisionDetector.collide(l, c));
		}
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(-1, 1), new Point(1, 1));

			Assert.assertTrue(collisionDetector.collide(l, c));
		}
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(-1, 0), new Point(1, 0));

			Assert.assertTrue(collisionDetector.collide(l, c));
		}
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(-1, -1), new Point(1, -1));

			Assert.assertTrue(collisionDetector.collide(l, c));
		}
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(-1, -1), new Point(1, -1.001));

			Assert.assertFalse(collisionDetector.collide(l, c));
		}
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(-1, -1.001), new Point(1, -1));

			Assert.assertFalse(collisionDetector.collide(l, c));
		}
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(-1, -1.001), new Point(1, -1.001));

			Assert.assertFalse(collisionDetector.collide(l, c));
		}
		{
			Circle c = new Circle(1, new Point(0, 0));
			Line l = new Line(new Point(-1, -1), new Point(1, 1));

			Assert.assertTrue(collisionDetector.collide(l, c));
		}
	}

	@Test
	public void circlesCollideTests() {
		{
			Circle c1 = new Circle(1, new Point(0, 0));
			Circle c2 = new Circle(1, new Point(1, 1));
			Assert.assertTrue(collisionDetector.collide(c1, c2));
		}
		{
			Circle c1 = new Circle(1, new Point(0, 0));
			Circle c2 = new Circle(2, new Point(2, 2));
			Assert.assertTrue(collisionDetector.collide(c1, c2));
		}
		{
			Circle c1 = new Circle(1, new Point(0, 0));
			Circle c2 = new Circle(1, new Point(0, 2));
			Assert.assertTrue(collisionDetector.collide(c1, c2));
		}
		{
			Circle c1 = new Circle(1, new Point(0, 0));
			Circle c2 = new Circle(1, new Point(2, 0));
			Assert.assertTrue(collisionDetector.collide(c1, c2));
		}
		{
			Circle c1 = new Circle(1, new Point(0, 0));
			Circle c2 = new Circle(0.9, new Point(2, 0));
			Assert.assertFalse(collisionDetector.collide(c1, c2));
		}
		{
			Circle c1 = new Circle(1, new Point(0, 0));
			Circle c2 = new Circle(1.1, new Point(1, 1));
			Assert.assertTrue(collisionDetector.collide(c1, c2));
		}
		{
			// special case (identical circles)
			Circle c1 = new Circle(1, new Point(0, 0));
			Circle c2 = new Circle(1, new Point(0, 0));
			Assert.assertTrue(collisionDetector.collide(c1, c2));
		}
		{
			// special case (circle within circle)
			Circle c1 = new Circle(1, new Point(0, 0));
			Circle c2 = new Circle(0.5, new Point(0, 0));
			Assert.assertFalse(collisionDetector.collide(c1, c2));
		}
	}
}
