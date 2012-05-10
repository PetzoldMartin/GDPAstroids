package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.*;

public class Sprite extends Figure {
	// TODO Kommentierung!
	public Vector vector;
	protected Point middlePoint;
	protected double rotationPhi;
	protected static ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public static void updateAll() {
		//FIXME interrates all sprites and update() them
//		Long runTime = System.currentTimeMillis();
		for (Sprite sprite : sprites) {
			sprite.update();
		}
//		try {
//			 Thread.sleep(GameController.globalFrameTime - System.currentTimeMillis() - runTime);
//			 } catch (IllegalArgumentException e) {
//			 System.out.println("Time Overload");
//			 }
//		System.out.println(runTime / GameController.globalFrameTime);
	}

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
		sprites.add(this);
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
//		Long runTime = System.currentTimeMillis();
		this.draw();
		this.move(vector);
//		return System.currentTimeMillis() - runTime;
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
		this.move(new Point(middlePoint.getX() - this.middlePoint.getX(),
				middlePoint.getY() - this.middlePoint.getY()));
		// FIXME setMiddlePoint must move the sprite to the right position
		// for Astroids Constructor and corner warp
	}

	public Point getMiddlePoint() {
		return middlePoint;
	}
}
