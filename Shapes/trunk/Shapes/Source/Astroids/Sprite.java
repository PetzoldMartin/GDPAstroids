package Astroids;

import java.awt.Color;

import Shapes.*;

public abstract class Sprite extends Figure {
	// TODO Kommentierung!
	//TODO make non abstract
	public Vector vector;
	protected Point middlePoint;
	protected double rotationPhi;

	/**
	 * Constructor for objects of class Sprite - overloaded
	 * 
	 * @param middlePoint
	 *            The middlePoint of the Sprite
	 * 
	 * @param vector
	 *            The movement-speed and movement-direction of the Sprite
	 */
	public Sprite() {
		this.middlePoint = new Point(0, 0);
		this.vector = new Vector(0, 0);
		this.rotationPhi=0;
		this.addShape(new Circle(1, this.getMiddlePoint(), Color.RED, false));

	}

	public Sprite(Point middlePoint) {
		this();
		this.middlePoint = middlePoint;
	}

	public Sprite(Point middlePoint, Vector vector) {
		this(middlePoint);
		this.vector = vector;
	}

	//TODO make non abstract
	public abstract void update() throws InterruptedException;

	public Sprite move() {
		return (Sprite) super.move(vector);
	}

	public Drawable rotate(double phi) {
		return this.rotate(middlePoint, phi);
	}

	public Point getMiddlePoint() {
		return middlePoint;
	}

	public void setMiddlePoint(Point middlePoint) {
		this.middlePoint = middlePoint;
	}

}
