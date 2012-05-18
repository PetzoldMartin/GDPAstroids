package Collision;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import Shapes.Circle;
import Shapes.Line;
import Shapes.Point;
import Shapes.Polygon;
import Shapes.Shape;

/**
 * Die {@link CollisionDetector#collide}-Methoden sind Beispiele fuer multiple dispatch (darf nicht mit Polymorphismus
 * verwechselt werden!).
 * 
 * Für einige Kollisionserkennungen gibt es zwei Implementierungen; sie sind austauschbar.
 * 
 * Methode {@link CollisionDetector#lineIntersectsLine2} zeigt ein Beispiel für den Umgang mit Rundungsfehlern (siehe
 * Variable epsilon).
 * 
 * Die Klasse Vector2D stammt aus der Apache Commons Math Bibliothek (Version 3) (siehe
 * http://commons.apache.org/math/).
 * 
 * @author Frank Grimm, Mai 2012 (unter Verwendung von Quellen von http://paulbourke.net/geometry/)
 */
public final class CollisionDetector {
	public boolean collide(Shape s1, Shape s2) {
		if (s1 instanceof Polygon)
			return collide((Polygon) s1, s2);
		else if (s2 instanceof Polygon)
			return collide((Polygon) s2, s1);
		else if (s1 instanceof Circle)
			return collide((Circle) s1, s2);
		else if (s2 instanceof Circle)
			return collide((Circle) s2, s1);
		else
			throw new IllegalArgumentException("TODO");
	}

	public boolean collide(Circle c, Shape s) {
		if (s instanceof Circle)
			return circleIntersectsCircle(c, (Circle) s);
		else if (s instanceof Line)
			return lineIntersectsCircle((Line) s, c);
		else
			throw new IllegalArgumentException("TODO");
	}

	public boolean collide(Polygon p, Shape s) {
		List<Point> points = p.getPoints();
		for (int i = 0; i < points.size(); i++) {
			Point p1 = points.get(i);
			Point p2;
			if ((i + 1) == points.size())
				p2 = points.get(0);
			else
				p2 = points.get(i + 1);

			Line l = new Line(p1, p2);

			if (collide(l, s))
				return true;
		}

		return false;
	}

	public boolean collide(Line l, Shape s) {
		if (s instanceof Line)
			return lineIntersectsLine(l, (Line) s);
		else if (s instanceof Polygon)
			return lineIntersectsPolygon(l, (Polygon) s);
		else if (s instanceof Circle)
			return lineIntersectsCircle(l, (Circle) s);
		else
			throw new IllegalArgumentException("TODO");
	}

	public boolean lineIntersectsPolygon(Line l, Polygon p) {
		List<Point> points = p.getPoints();
		for (int i = 0; i < points.size(); i++) {
			Point p1 = points.get(i);
			Point p2;
			if ((i + 1) == points.size())
				p2 = points.get(0);
			else
				p2 = points.get(i + 1);

			Line l2 = new Line(p1, p2);

			if (lineIntersectsLine(l, l2))
				return true;
		}
		return false;
	}

	/** Returns cross product of given vectors. */
	private static double cross(Vector2D v1, Vector2D v2) {
		return v1.getX() * v2.getY() - v2.getX() * v1.getY();
	}

	/** @see http://paulbourke.net/geometry/lineline2d/ */
	public boolean lineIntersectsLine(Line l1, Line l2) {
		Point l1p1 = l1.getPoints().get(0);
		Point l1p2 = l1.getPoints().get(1);

		Point l2p1 = l2.getPoints().get(0);
		Point l2p2 = l2.getPoints().get(1);

		double l1x1 = l1p1.getX();
		double l1y1 = l1p1.getY();
		double l1x2 = l1p2.getX();
		double l1y2 = l1p2.getY();

		double l2x1 = l2p1.getX();
		double l2y1 = l2p1.getY();
		double l2x2 = l2p2.getX();
		double l2y2 = l2p2.getY();

		// Denominator for ua and ub are the same
		double d = (l2y2 - l2y1) * (l1x2 - l1x1) - (l2x2 - l2x1) * (l1y2 - l1y1);

		// n_a and n_b are calculated as separate values for readability.
		double na = (l2x2 - l2x1) * (l1y1 - l2y1) - (l2y2 - l2y1) * (l1x1 - l2x1);
		double nb = (l1x2 - l1x1) * (l1y1 - l2y1) - (l1y2 - l1y1) * (l1x1 - l2x1);

		// If na and nb are both equal to zero the lines are on top of each other (coincidental).
		if (na == 0 && nb == 0)
			return true;

		// 0 indicates that the lines are parallel.
		if (d == 0)
			return false;

		// Calculate the intermediate fractional point that the lines potentially intersect.
		double ua = na / d;
		double ub = nb / d;

		// The fractional point will be between 0 and 1 inclusive if the lines intersect. If the fractional calculation
		// is larger than 1 or smaller than 0 the lines would need to be longer to intersect.
		return ua >= 0d && ua <= 1d && ub >= 0d && ub <= 1d;
	}

	/**
	 * Beispiel für den Umgang mit Rundungsfehlern; siehe Variable epsilon. Hier im Beispiel ist epsilon = 10^-6.
	 * 
	 * Will man bei Berechnungen auf Rundungsfehler eingehen, so ist ein berechneter und eventuell durch Rundungsfehler
	 * ungenauer Wert a ist gleich einem anderen Wert b, wenn sich a im Bereich [b-e; b+e] befindet; also: a >= (b-e) &&
	 * a <= (b+e).
	 * 
	 * 
	 * @see http://paulbourke.net/geometry/lineline2d/
	 */
	public boolean lineIntersectsLine2(Line l1, Line l2) {
		// epsilon (to deal with rounding errors).
		double epsilon = 10e-6;

		Vector2D p = createVector(l1.getPoints().get(0));
		Vector2D r = createVector(l1.getPoints().get(1));
		r = r.subtract(p);

		Vector2D q = createVector(l2.getPoints().get(0));
		Vector2D s = createVector(l2.getPoints().get(1));
		s = s.subtract(q);

		double rCrossS = cross(r, s);

		if (rCrossS <= epsilon && rCrossS >= -1 * epsilon) {
			// lines are parallel, but don't intersect.
			return false;
		}
		double t = cross(q.subtract(p), s) / rCrossS;
		double u = cross(q.subtract(p), r) / rCrossS;
		if (0 <= u && u <= 1 && 0 <= t && t <= 1)
			// lines do intersect.
			return true;
		else
			// lines do not intersect.
			return false;
	}

	/**
	 * @see http://paulbourke.net/geometry/sphereline/
	 */
	public boolean lineIntersectsCircle(Line li, Circle ci) {
		// p1 is the starting point of the line.
		Vector2D p1 = createVector(li.getPoints().get(0));

		// p2 is the end point of the line.
		Vector2D p2 = createVector(li.getPoints().get(1));

		// p3 is the center of the circle we're testing against.
		Vector2D p3 = new Vector2D(ci.getCenter().getX(), ci.getCenter().getY());

		// r is the radius of that circle.
		double r = ci.getRadius();

		// direction vector of line, from start to end.
		Vector2D d = p2.subtract(p1);
		// vector from center of circle to start of line.
		Vector2D f = p1.subtract(p3);

		double a = d.dotProduct(d);
		double b = 2 * f.dotProduct(d);
		double c = f.dotProduct(f) - r * r;

		double discriminant = b * b - 4 * a * c;

		if (discriminant < 0)
			// no intersection
			return false;

		// line and circle might intersect. we are just interested in whether they do intersect, not the actual
		// solutions. so, it is sufficient to determine the first solution (if any).
		discriminant = Math.sqrt(discriminant);
		double t1 = (-b + discriminant) / (2 * a); // first solution (intersection point)
		// double t2 = (-b - discriminant) / (2 * a); // second solution (intersection point)

		// line intersects with circle if 0 <= t1 <= 1.
		return t1 >= 0 && t1 <= 1;
	}

	private static Vector2D createVector(Point p) {
		return new Vector2D(p.getX(), p.getY());
	}

	private static double square(double d) {
		return d * d;
	}

	/**
	 * Ähnlicher Ansatz wie {@link #lineIntersectsCircle(Line, Circle)}.
	 * 
	 * @see http://paulbourke.net/geometry/sphereline/
	 */
	public boolean lineIntersectsCircle2(Line li, Circle ci) {
		Point p1 = li.getPoints().get(0);
		Point p2 = li.getPoints().get(1);

		double lx1 = p1.getX();
		double ly1 = p1.getY();
		double lx2 = p2.getX();
		double ly2 = p2.getY();
		double cx = ci.getCenter().getX();
		double cy = ci.getCenter().getY();
		double r = ci.getRadius();

		double a = square(lx2 - lx1) + square(ly2 - ly1);
		double b = 2 * ((lx2 - lx1) * (lx1 - cx) + (ly2 - ly1) * (ly1 - cy));
		double c = square(cx) + square(cy) + square(lx1) + square(ly1) - 2 * (cx * lx1 + cy * ly1) - square(r);

		double i = b * b - 4.0 * a * c;

		// Only if i >= 0, the line and the circle intersect.
		return i >= 0.0;
	}

	/** http://paulbourke.net/geometry/2circle/ */
	public boolean circleIntersectsCircle(Circle c1, Circle c2) {
		{
			// Calculate distance between centres of circles
			Vector2D cv1 = new Vector2D(c1.getCenter().getX(), c1.getCenter().getY());
			Vector2D cv2 = new Vector2D(c2.getCenter().getX(), c2.getCenter().getY());
			double d = cv1.distance(cv2);

			double c1r = c1.getRadius();
			double c2r = c2.getRadius();
			double m = c1r + c2r;
			double n = Math.abs(c1r - c2r);

			if (d > m)
				// No solutions.
				return false;
			else if (d < n)
				// Circles are contained within each other.
				return false;
			else if (d == 0 && c1r == c2r)
				// Circles are identical.
				return true;
			else
				// Circles truly intersect.
				return true;
		}
	}
}
