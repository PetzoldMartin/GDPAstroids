package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.Circle;
import Shapes.Point;
import Shapes.Polygon;

public class Astroid extends Sprite {
	// TODO commenting
	private int edge;

	public Astroid() {
		this(24, 30);
	}

	public Astroid(int edge, int radius) {
		super();
		this.edge = edge;
		this.radius = radius;
		this.addShape(new Circle(radius, this.getCenterPoint(), Color.RED,
				false));
		ArrayList<Point> astroList = new ArrayList<Point>();
		for (int phi = 0; phi < 360; phi += 360 / edge) {
			astroList.add(new Vector(radius - radius / (radius / 6)
					* Math.random(), phi));
		}
		this.addShape(new Polygon(astroList, Color.WHITE, false));
		this.setVector(new Vector(1, Math.random() * 360));
		this.rotationPhi = Math.random() * 12 - 6;
		// TODO places Astroids only at the corners!
		this.move(new Point(Math.random() * gameController.getGameScreenX() * 2
				- gameController.getGameScreenX(), Math.random()
				* gameController.getGameScreenY() * 2
				- gameController.getGameScreenY()));
	}

	@Override
	public void update() {
		this.rotate(this.rotationPhi);
		super.update();
	}

	@Override
	public void destroy() {
		split();
		gameController.removeSprites(this);
	}

	public void split() {
		// TODO Spiltmethode
		for (int i = 0; i < 2; i++) {
			new Astroid(edge / 2, radius * 2 / 3);
		}
	}
}
