package Shapes;

import java.awt.Color;

/**
 * class that manage a rectangle as an object subclass of Polygon
 * 
 * @author (Markus Krummnacker)
 * @version (0.3)
 */
public class Rectangle extends Polygon {
	private double phi;
	Point dim;

	/**
	 * Constructors for objects of class Rectangle - overloaded
	 * 
	 * @param central
	 *            startpoint of the rectangle ( central point )
	 * @param lx
	 *            width of the rectangle
	 * @param ly
	 *            height of the rectangle
	 * @param color
	 *            color of the rectangle
	 * @param solid
	 *            choice is the rectangle fullfilled
	 */
	public Rectangle(Point central, double lx, double ly) {
		points.add(central);
		this.dim = new Point(lx, ly);
		phi = 0;
	}

	public Rectangle(Point central, double lx, double ly, Color color,
			boolean solid) {
		this(central, lx, ly);
		setColor(color);
		setSolid(solid);
	}

	/**
	 * draw the shape rectangle on the whiteboard
	 */
	@Override
	public void draw() {
		getWhiteBoard().removeShape(representation);
		representation = getWhiteBoard().drawRectangle(points.get(0).getX(),
				points.get(0).getY(), dim.getX(), dim.getY(), this.getColor(),
				this.isSolid(), phi);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Shapes.Polygon#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Rectangle) {
			Rectangle otherRectangle = (Rectangle) obj;
			if (this.dim.getX() == (otherRectangle.dim.getX())
					& this.dim.getY() == (otherRectangle.dim.getY())
					& this.phi == (otherRectangle.phi)
					& this.points.equals(otherRectangle.points)) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	/**
	 * rotate the Rectangle on the whiteboard around center
	 * 
	 * @param center
	 *            point to rotate around
	 * @param phi
	 *            angel to rotate return itself as object
	 */
	@Override
	public Rectangle rotate(Point center, double phi) {
		super.rotate(center, phi);
		this.phi -= Math.toRadians(phi) % (Math.PI * 2);
		return this;
	}
}
