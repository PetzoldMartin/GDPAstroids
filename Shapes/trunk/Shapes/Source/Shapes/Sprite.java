package Shapes;

import java.util.ArrayList;
import java.awt.Color;

public class Sprite extends Figure { 		
	// TODO Kommentierung!
	// TODO instanz variablen erst im Konstuktor initialisieren!
	public Vector movement = new Vector(0, 0); 	
	// TODO change name of movemnet to vector
	private Point middlePoint = new Point(0, 0);

	public Sprite() {
		this.addShape(new Circle(1, this.getMiddlePoint(), Color.RED, false));
	}

	public Sprite(Point middlePoint) {
		this();
		this.middlePoint = middlePoint;
	}
	//TODO Constructor with middlePoint and movement
	public void update() {
		this.draw();
		this.move(movement);
	}

	public Sprite move() {
		return (Sprite) super.move(movement);
	}
	
	public Drawable rotate(double phi) {
		return this.rotate(middlePoint, phi);
	}

	public static Sprite ship() {
		Sprite ship = new Sprite();
		ArrayList<Point> shipList = new ArrayList<Point>();
		shipList.add(new Point(-10, -10));
		shipList.add(new Point(10, -10));
		shipList.add(new Point(0, 15));
		ship.addShape(new Polygon(shipList, Color.RED, false));
		return ship;

	}

	public Point getMiddlePoint() {
		return middlePoint;
	}

	public void setMiddlePoint(Point middle) {
		this.middlePoint = middle;
	}

}
