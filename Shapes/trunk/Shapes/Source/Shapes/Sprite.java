package Shapes;

import java.util.ArrayList;
import java.awt.Color;

public class Sprite extends Figure{
	Vector movement= new Vector(0,0);
	private Point middlePoint = new Point(0, 0);
	
	public Sprite(Point middlePoint) {
		this.middlePoint = middlePoint;
		this.addShape(new Circle(1, this.getMiddlePoint(), Color.RED, false));
	}
	
	public Sprite() {
		this.addShape(new Circle(1, this.getMiddlePoint(), Color.RED, false));
	}
	
	public Drawable rotate(double phi) {
		return this.rotate(middlePoint, phi);
	}
	public Point getMiddlePoint() {
		return middlePoint;
	}

	public void setMiddlePoint(Point middle) {
		this.middlePoint = middle;
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
	public void update()
	{
		this.draw();
	}
	
	
}
