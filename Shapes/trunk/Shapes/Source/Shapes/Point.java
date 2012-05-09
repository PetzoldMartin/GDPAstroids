package Shapes;

/**
 * class that manage x and y cordinates of points as an object
 * 
 * @author Markus Krummnacker
 * @version (0.3)
 */
public class Point {
	protected double x, y;

	/**
	 * Constructor for objects of class Point
	 * 
	 * @param x
	 *            x-cordinate of the point
	 * @param y
	 *            y-cordinate of the point
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * move the point by dx & dy on Whiteboard
	 * 
	 * @param trans
	 *            point to translate with dx and dy
	 */
	public Point move(Point trans) {
		this.x += trans.getX();
		this.y += trans.getY();
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
	public void rotate(Point center, double phi) {
		if (!center.equals(this)) {
			phi = Math.toRadians(phi);
			Point temp = this.copy().move(
					new Point(-1 * center.getX(), -1 * center.getY()));
			this.x = temp.getX() * Math.cos(phi) + temp.getY() * Math.sin(phi);
			this.y = -1 * temp.getX() * Math.sin(phi) + temp.getY()
					* Math.cos(phi);
			this.move(center);
		}
	}

	/**
	 * @return return a new point that is a copy of this one
	 */
	public Point copy() {
		return new Point(getX(), getY());
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
		if (obj instanceof Point) {
			Point otherPoint = (Point) obj;
			if (this.getX() == (otherPoint.getX())
					& this.getY() == (otherPoint.getY())) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	/**
	 * @return
	 */
	public Point checkCorner() {
		if (Math.abs(this.x) >= Main.windowX) {
			if (this.x >= Main.windowX) {
				return new Point(-Main.windowX,0);
			} else {
				return new Point(Main.windowX,0);
			}
		}
		if (Math.abs(this.y) >= Main.windowY) {
			if (this.y >= Main.windowY) {
				return new Point(0,-Main.windowY);
			} else {
				return new Point(0,Main.windowY);
			}
		}
		return new Point(0, 0);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}