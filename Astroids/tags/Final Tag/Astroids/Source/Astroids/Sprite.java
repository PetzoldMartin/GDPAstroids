package Astroids;

import java.awt.Color;

import Shapes.Circle;
import Shapes.Drawable;
import Shapes.Figure;
import Shapes.Point;
import Shapes.Shape;

/**
 * abstract class that manage moveable objects with a Vector & a CenterPoint
 * (for self-rotation)
 * 
 * @author Markus Krummnacker, Martin Petzold
 * @version (0.4)
 * 
 */
public abstract class Sprite extends Figure {
	protected static GameController gameController;
	/**
	 * @param gameController
	 *            GameController to set
	 */
	public static void setGameController(GameController gameController) {
		Sprite.gameController = gameController;
	}
	protected double radius = 0;
	private Vector vector;
	private Point centerPoint;

	protected double rotationPhi;

	// TODO overlay implementation
	// protected ArrayList<Drawable> overlay = new ArrayList<Drawable>();
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
		gameController.addSprites(this);
		this.centerPoint = new Point(0, 0);
		this.vector = new Vector(0, 90);
		this.rotationPhi = 0;
		this.addShape(new Circle((int) radius, this.centerPoint, Color.BLACK,
				false));
	}

	protected void changeDirection(double phi) {
		vector.changeDirection(phi);
	}

	protected void changeSpeed(double amount) {
		vector.changeSpeed(amount);
	}

	/**
	 * checks collision of this sprite
	 * 
	 * @param otherSprite
	 *            sprite to check
	 */
	public boolean collision(Sprite otherSprite) {
		if (radiusCollision(otherSprite)) {
			if (realCollision(otherSprite)) {
				otherSprite.destroy(this);
				// this.destroy(otherSprite);
				return true;
			}
		}
		return false;
	}

	/**
	 * destroy the Object by an collider
	 * 
	 * @param collider
	 *            sprite that destroy the object
	 */
	public abstract void destroy(Sprite collider);

	// TODO implemts effects

	protected double getAmount() {
		return vector.getAmount();
	}

	/**
	 * @return a copy of the CenterPoint of this Sprite
	 */
	public Point getCenterPoint() {
		return this.centerPoint.copy();
	}

	/**
	 * check the Sprite position and if it is out of GameScreen return the right
	 * warping point to move
	 * 
	 * @return Point to move to the other corner
	 */
	protected Point getEdgeWarp() {
		Point returnPoint = new Point(0, 0);
		if (Math.abs(this.centerPoint.getX()) >= gameController
				.getGameScreenX()) {
			if (this.centerPoint.getX() >= gameController.getGameScreenX()
					- gameController.getAstroSize()) {
				returnPoint.move(-gameController.getGameScreenX() * 2, 0); // right
			} else {
				returnPoint.move(gameController.getGameScreenX() * 2, 0); // left
			}
		}
		if (Math.abs(this.centerPoint.getY()) >= gameController
				.getGameScreenY()) {
			if (this.centerPoint.getY() >= gameController.getGameScreenY()) {
				returnPoint.move(0, -gameController.getGameScreenY() * 2); // upper
			} else {
				returnPoint.move(0, gameController.getGameScreenY() * 2); // bottom
			}
		}
		return returnPoint;
	}

	protected double getPhi() {
		return vector.getPhi();
	}

	public Vector getVector() {
		return vector;
	}

	// public Sprite addOverlayShape(Drawable aDrawable) {
	// overlay.add(aDrawable);
	// return this;
	// }

	/**
	 * move the Sprite by the Vector around the drawboard
	 * 
	 * @return this Sprite
	 */
	public Sprite move() {
		return (Sprite) super.move(vector);
	}

	/**
	 * checks collision of this sprite by max radius (level1)
	 * 
	 * @param otherSprite
	 *            sprite to check
	 * @return true if collide
	 */
	private boolean radiusCollision(Sprite otherSprite) {
		double distance = new Vector(this.getCenterPoint().invert()
				.move(otherSprite.getCenterPoint())).getAmount();
		if (distance < (this.radius + otherSprite.radius)) {
			if (realCollision(otherSprite)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * checks collision of this sprite by the real shapes (level2)
	 * 
	 * @param otherSprite
	 *            sprite to check
	 * @return true if collide
	 */
	private boolean realCollision(Sprite otherSprite) {
		// TODO make it Threaded
		for (Drawable s1 : this.getShapes()) {
			for (Drawable s2 : otherSprite.getShapes()) {
				if (gameController.getCollisionDetector().collide((Shape) s1,
						(Shape) s2)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * rotate the Sprite around his CenterPoint
	 * 
	 * @param phi
	 *            Angel to rotate
	 * @return
	 */
	protected Drawable rotate(double phi) {
		return this.rotate(centerPoint, phi);
	}

	/**
	 * move the Sprite to a new position on drawboard
	 * 
	 * @param centerPoint
	 *            new CenterPoint of the Sprite
	 */
	protected void setCenterPoint(Point centerPoint) {
		this.move(this.centerPoint.invert());
		this.move(centerPoint);
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}

	/**
	 * Update the Sprite by one tact (frame) move the Sprite, checks corner
	 * collision and warp to the other corner
	 */
	public void update() {
		this.move(vector);
		Point cornerWarp = getEdgeWarp();
		if (!cornerWarp.equals(new Point(0, 0))) {
			move(cornerWarp);
		}
	}
}
