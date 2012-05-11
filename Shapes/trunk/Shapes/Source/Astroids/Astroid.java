package Astroids;

import java.awt.Color;

import Shapes.*;

public class Astroid extends Sprite {
	//TODO commenting
	public Astroid() {
		super(new Point(Math.random() * OldGameController.windowX
				- OldGameController.windowX / 2, Math.random()
				* OldGameController.windowY - OldGameController.windowY / 2),
				new Vector(Math.random() * 10, Math.random() * 360));
		this.rotationPhi = Math.random() * 45;
		building(middlePoint);
	}

	public Astroid(Point middlePoint, Vector vector, double rotationPhi) {
		super(middlePoint, vector);
		// TODO setMiddlePoint must move the sprite to the right position
		this.rotationPhi = rotationPhi;
		building(middlePoint);
	}

	private void building(Point middlePoint) {
		this.addShape(new Rectangle(middlePoint, 10, 10, Color.WHITE, false));
		this.addShape(new Circle(Math.round(15), middlePoint, Color.RED, false));
	}

	@Override
	public void update() {
		super.update();
		rotate(this.rotationPhi);
	}
}
