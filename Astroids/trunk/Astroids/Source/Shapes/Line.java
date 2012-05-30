package Shapes;

import java.awt.Color;

/**
 * class that manage the two endpoints of a line as an object subclass of
 * Polygon
 * 
 * @author (Markus Krummnacker)
 * @version (0.3)
 */
public class Line extends Polygon {

	/**
	 * Constructors for objects of class Line - overloaded
	 * 
	 * @param start
	 *            startpoint of the line
	 * @param end
	 *            endpoint of the line
	 * @param color
	 *            color of the line
	 */
	public Line(Point start, Point end) {
		points.add(start);
		points.add(end);
	}

	public Line(Point start, Point end, Color color) {
		this(start, end);
		setColor(color);
	}
}
