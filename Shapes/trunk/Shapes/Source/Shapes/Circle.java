package Shapes;

import java.awt.Color;

/**
 * class that manage a circle as an object subclass of Shape
 * 
 * @author (Markus Krummnacker, Martin Petzold)
 * @version (0.3)
 */
public class Circle extends Shape {
	private int radius;
	private Point center;

	/**
	 * Constructor for objects of class Circle - overloaded
	 * 
	 * @param radius
	 *            radius of the circle
	 * @param center
	 *            center point of the circle
	 * @param color
	 *            color of the circle
	 * @param solid
	 *            choice is the circle fullfilled
	 */
	public Circle(int radius, Point center) {
		this.radius = radius;
		this.center = center;
	}

	public Circle(int radius, Point center, Color color, boolean solid) {
		this(radius, center);
		setColor(color);
		setSolid(solid);
	}

	/**
	 * draw the shape circle on the whiteboard
	 */
	@Override
	public void draw() {
		this.draw(this.color, this.solid);
	}

	/**
	 * draw the shape circle on the whiteboard
	 * 
	 * @param color
	 *            color of the circle
	 * @param solid
	 *            choice is the circle fullfilled
	 */
	public void draw(Color color, boolean solid) {
		getWhiteBoard().removeShape(representation);
		representation = getWhiteBoard().drawCircle(center.getX(),
				center.getY(), radius, color, solid);
	}

	/**
	 * move the circle on the whiteboard
	 * 
	 * @param dx
	 *            x var to move by the x scale
	 * @param dy
	 *            x var to move by the y scale return itself as object
	 */
	@Override
	public Drawable move(Point trans) {
		center.move(trans);
		return this;
	}

	/**
	 * rotate the point around a center point
	 * 
	 * @param center
	 *            point to rotate around
	 * @param phi
	 *            angel to rotate
	 */
	@Override
	public Drawable rotate(Point center, double phi) {
		this.center.rotate(center, phi);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Circle) {
			Circle otherCircle = (Circle) obj;
			if (this.getCenter().equals((otherCircle.getCenter()))
					& this.getRadius() == (otherCircle.getRadius())) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public Point getCenter() {
		return center;
	}

	public int getRadius() {
		return radius;
	}
}
