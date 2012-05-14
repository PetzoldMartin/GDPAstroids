package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.Circle;
import Shapes.Point;
import Shapes.Polygon;
import Shapes.Rectangle;

public class Astroid extends Sprite {
	// TODO commenting
	// FIXME 2 Circle in one sprite collide!!!
	public Astroid() {
		super();
//		this.addShape(new Circle(15, this.getCenterPoint(), Color.RED, false));
		ArrayList<Point> astroList = new ArrayList<Point>();
		astroList.add(new Point(-10, -10));
		astroList.add(new Point(-10, 10));
		astroList.add(new Point(10, 10));
		astroList.add(new Point(10, -10));
		this.addShape(new Polygon(astroList, Color.WHITE, false));
		this.vector = new Vector(1, Math.random() * 360);
		this.rotationPhi = Math.random() * 12;
		this.move(new Point(Math.random() * gameController.getWindowX() * 2
				- gameController.getWindowX(), Math.random()
				* gameController.getWindowY() * 2 - gameController.getWindowY()));
	}

	@Override
	public void update() {
		super.update();
		this.rotate(this.rotationPhi);
	}
}
