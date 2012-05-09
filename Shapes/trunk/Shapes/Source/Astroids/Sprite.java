package Astroids;

import java.util.ArrayList;
import java.awt.Color;

import Shapes.Circle;
import Shapes.Drawable;
import Shapes.Figure;
import Shapes.Point;

public abstract class Sprite extends Figure {
	// TODO Kommentierung!
	
	public Vector vector ;
	protected Point middlePoint ;
	protected double rotationPhi;

	public Sprite() {
		this.middlePoint= new Point(0, 0);
		this.vector=new Vector(0,0);
		this.addShape(new Circle(1, this.getMiddlePoint(), Color.RED, false));
		
	}

	public Sprite(Point middlePoint) {
		this();
		this.middlePoint = middlePoint;
		this.move(middlePoint);
	}
	
	public Sprite(Point middlePoint, Vector vector){
		this(middlePoint);
		this.vector= vector;
	}

	public abstract void update();

	// TODO Wenn direction seit dem letztem Aufruf von update() veraendert wurde, dann wird shape.rotate() aufgerufen. 
	// Dies soll also nur bei einer Veraenderung passieren damit die Shape nicht st�ndig rotiert. Zur Fortbewegung 
	// werden das Delta-x und -y berechnet und in shape.move() verwendet. Diese Methode wird immer aufgerufen, 
	// damit sich die Shape bei jedem update() fortbewegt (es sei denn, speed ist 0).
	
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
