package Shapes;

import java.util.ArrayList;
import java.awt.Color;

public class Sprite extends Figure {
	// TODO Kommentierung!
	// TODO instanz variablen erst im Konstuktor initialisieren!
	public Vector vector = new Vector(0, 0);
	private Point middlePoint = new Point(0, 0);

	// TODO implement an variable for rotaion that control the self rotation and
	// rotate in update() --> usefull for Astroids!
	public Sprite() {
		this.addShape(new Circle(1, this.getMiddlePoint(), Color.RED, false));
	}

	public Sprite(Point middlePoint) {
		this();
		this.middlePoint = middlePoint;
	}

	// TODO Constructor with middlePoint and movement
	public void update() {
		this.draw();
		this.move(vector);
	}
	// TODO Wenn direction seit dem letztem Aufruf von update() ver�ndert wurde, dann wird shape.rotate() aufgerufen. 
	// Dies soll also nur bei einer Ver�nderung passieren damit die Shape nicht st�ndig rotiert. Zur Fortbewegung 
	// werden das Delta-x und -y berechnet und in shape.move() verwendet. Diese Methode wird immer aufgerufen, 
	// damit sich die Shape bei jedem update() fortbewegt (es sei denn, speed ist 0).
	
	public Sprite move() {
		return (Sprite) super.move(vector);
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
		ship.addShape(new Polygon(shipList, Color.WHITE, false));
		return ship;

	}

	public Point getMiddlePoint() {
		return middlePoint;
	}

	public void setMiddlePoint(Point middle) {
		this.middlePoint = middle;
	}

}
