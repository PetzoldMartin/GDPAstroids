package Shapes;

import java.util.ArrayList;
import java.awt.Color;

import ShapeExceptions.PolygoneShapeException;

/**
 * class that manage a polygons as an object subclass of Shape
 * 
 * @author (Markus Krummnacker)
 * @version (0.3)
 */
public class Polygon extends Shape {
	protected ArrayList<Point> points = new ArrayList<Point>();

	/**
	 * Constructor for objects of class Polygon
	 */
	public Polygon() {

	}

	public Polygon(ArrayList<Point> points) {
		this.points = points;
	}

	public Polygon(ArrayList<Point> points, Color color, boolean solid) {
		this.points = points;
		setColor(color);
		setSolid(solid);
	}

	/**
	 * draw the shape polygon on the whiteboard
	 */
	@Override
	public void draw() {
		getWhiteBoard().removeShape(representation);
		double[] x = new double[points.size()];
		double[] y = new double[points.size()];
		int i = 0;
		for (Point point : points) {
			x[i] = point.getX();
			y[i] = point.getY();
			i++;
		}
		representation = getWhiteBoard().drawPolygon(x, y, this.getColor(),
				this.isSolid(), 0);
	}

	/**
	 * move the polygon on the whiteboard
	 * 
	 * @param dx
	 *            x var to move by the x scale
	 * @param dy
	 *            x var to move by the y scale return itself as object
	 */
	@Override
	public Polygon move(Point trans) {
		for (Point point : points) {
			point.move(trans);
		}
		return this;
	}

	/**
	 * rotate the polygon on the whiteboard around center
	 * 
	 * @param center
	 *            point to rotate around
	 * @param phi
	 *            angel to rotate return itself as object
	 */
	@Override
	public Polygon rotate(Point center, double phi) {
		for (Point point : points) {
			point.rotate(center, phi);
		}
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Polygon) {
			Polygon otherPolygon = (Polygon) obj;
			if (points.equals(otherPolygon.getPoints())) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * set points of the polygon - overloaded
	 * 
	 * @param points
	 *            Arraylist of points of the polygon
	 * @param color
	 *            color of the polygon
	 * @param solid
	 *            choice is the polygon fullfilled
	 *            
	 * @throws PolygoneShapeException
	 * 				this exeption throws if the Shape has less then 2 Points
	 */
	public void setPoints(ArrayList<Point> points) throws PolygoneShapeException {
		if(points.size()<=2)
    	{
    		throw new PolygoneShapeException();
    	}
		this.points = points;
	}

	public void setPoints(ArrayList<Point> points, Color color, boolean solid) {
		this.points = points;
		setColor(color);
		setSolid(solid);
	}
}
