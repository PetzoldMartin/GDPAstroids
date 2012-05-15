package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.Circle;
import Shapes.Point;
import Shapes.Polygon;
import Shapes.Rectangle;

public class Astroid extends Sprite {
	// TODO commenting
	// TODO create Astroide with max radius & edge count! 
	private int edge;
	private int radius;
	public Astroid() {
		this(24,30);
	}
	public Astroid(int edge, int radius) {
		super();
 		this.edge=edge;
 		this.radius=radius;
//		this.addShape(new Circle(radius, this.getCenterPoint().copy(), Color.RED, false));
		ArrayList<Point> astroList = new ArrayList<Point>();
		for (int phi =0; phi < 360; phi+=360/edge) {
				astroList.add(new Vector(radius-radius/edge*8*Math.random(), phi));				
		}
		this.addShape(new Polygon(astroList, Color.WHITE, false));
		this.vector = new Vector(1, Math.random() * 360);
		this.rotationPhi = Math.random() * 6;
		this.move(new Point(Math.random() * gameController.getWindowX() * 2
				- gameController.getWindowX(), Math.random()
				* gameController.getWindowY() * 2 - gameController.getWindowY()));
	}
	@Override
	public void update() {
		super.update();
		this.rotate(this.rotationPhi);
	}public void collide() {
		//TODO implemets destroy astro and create 2 new smaller halt of radius and edges
	}
	
}
