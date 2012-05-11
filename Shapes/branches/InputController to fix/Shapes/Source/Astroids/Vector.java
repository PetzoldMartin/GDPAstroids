package Astroids;

import Shapes.Point;

/**
 * class that manage a Vector with x and y and also with amount as lenght and
 * phi as angel
 * 
 * @author Markus Krummnacker
 * @version (0.1)
 * 
 */
public class Vector extends Shapes.Point {
	private double amount;
	private double phi;

	public Vector() {
		super(0, 0);
		this.amount = 0;
		this.phi = 0;
	}

	/**
	 * Create Vector
	 * 
	 * @param amount
	 *            lenght of the vector
	 * @param phi
	 *            angel of the vector
	 */
	public Vector(double amount, double phi) {
		super(amount * Math.cos(Math.toRadians(phi)), amount
				* Math.sin(Math.toRadians(phi)));
		this.amount = amount;
		this.phi = phi;
	}

	/**
	 * Create Vector
	 * 
	 * @param vector
	 */
	public Vector(Point vector) {
		super(vector.getX(), vector.getY());
		this.amount = Math.sqrt(Math.pow(this.getX(), 2.0)
				+ Math.pow(this.getY(), 2.0));
		this.phi = Math.toDegrees(Math.atan(vector.getY() / vector.getX()));
	}

	/**
	 * Set this Vector by a Point
	 * 
	 * @param vector
	 */
	private void setVector(Point vector) {
		this.setX(vector.getX());
		this.setY(vector.getY());
		this.amount = Math.sqrt(Math.pow(this.getX(), 2.0)
				+ Math.pow(this.getY(), 2.0));
		this.phi = Math.toDegrees(Math.atan(vector.getY() / vector.getX()));
	}

	/**
	 * Set this Vector by amount and phi
	 * 
	 * @param amount
	 *            lenght of the vector
	 * @param phi
	 *            angel of the vector
	 */
	private void setVector(double amount, double phi) {
		this.amount = amount;
		this.phi = phi;
		this.x = amount * Math.cos(Math.toRadians(phi));
		this.y = amount * Math.sin(Math.toRadians(phi));
	}

	/**
	 * Change this Vector by amount and phi
	 * 
	 * @param amount
	 *            lenght of the vector
	 * @param phi
	 *            angel of the vector
	 * @return this
	 */
	public Vector changeVector(double amount, double phi) {
		setVector(this.amount + amount, (this.phi - phi) % 360);
		return this;
	}

	/**
	 * Change this Vector by a Point
	 * 
	 * @param vector
	 * @return this
	 */
	public Vector changeVector(Point vector) {
		setVector(this.move(vector));
		return this;
	}

	/**
	 * Change only the phi of this vector
	 * 
	 * @param phi
	 *            angel of the vector
	 * @return this
	 */
	public Vector changeDirection(double phi) {
		return changeVector(0, phi);
	}

	/**
	 * Change only the amount of this vector
	 * 
	 * @param amount
	 *            lenght of the vector
	 * @return this
	 */
	// TODO implement a max speed
	public Vector changeSpeed(double amount) {
		return changeVector(amount, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Shapes.Point#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Vector) {
			Point otherVector = (Vector) obj;
			if (this.getX() == (otherVector.getX())
					& this.getY() == (otherVector.getY())) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public double getAmount() {
		return amount;
	}

	public double getPhi() {
		return phi;
	}
}
