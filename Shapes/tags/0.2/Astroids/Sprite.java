package Astroids;

import java.awt.Color;

import Shapes.Circle;
import Shapes.Drawable;
import Shapes.Figure;
import Shapes.Point;

public class Sprite extends Figure {
	// TODO Kommentierung!
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
		if (!(this instanceof SpaceShip)) {
			GameController.sprites.add(this);
		}
		this.middlePoint = new Point(0, 0);
		this.vector = new Vector(0, 0);
		this.rotationPhi = 0;
		this.addShape(new Circle(1, this.getMiddlePoint(), Color.RED, false));

	}

	public Sprite(Point middlePoint) {
		this();
		this.move(middlePoint);
	}

	public Sprite(Point middlePoint, Vector vector) {
		this(middlePoint);
		this.vector = vector;
	}

	public void update() {
		this.draw();
		this.move(vector);
	}

	public Sprite move() {
		return (Sprite) super.move(vector);
	}

	public Drawable rotate(double phi) {
		return this.rotate(middlePoint, phi);
	}

	/**
	 * @return Point to move to the other corner
	 */
	protected Point checkCorner() {
		if (Math.abs(this.middlePoint.getX()) >= GameController.windowX) {
			if (this.middlePoint.getX() >= GameController.windowX) {
				return new Point(-GameController.windowX, 0);
			} else {
				return new Point(GameController.windowX, 0);
			}
		}
		if (Math.abs(this.middlePoint.getY()) >= GameController.windowY) {
			if (this.middlePoint.getY() >= GameController.windowY) {
				return new Point(0, -GameController.windowY);
			} else {
				return new Point(0, GameController.windowY);
			}
		}
		return new Point(0, 0);
	}

	protected void setMiddlePoint(Point middlePoint) {
		this.move(this.middlePoint.invert());
		this.move(middlePoint.copy());
	}

	public Point getMiddlePoint() {
		return middlePoint;
	}
}
