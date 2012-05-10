package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.Point;
import Shapes.Polygon;

public class SpaceShip extends Sprite {

	public SpaceShip() {
		super();
		ArrayList<Point> shipList = new ArrayList<Point>();
		shipList.add(new Point(-10, -10));
		shipList.add(new Point(-10, 10));
		shipList.add(new Point(15, 0));
		this.addShape(new Polygon(shipList, Color.WHITE, false));
	}

	@Override
	public void update() {
		// Long runTime = 
		super.update();
		// synchronized (runTime) {
		// runTime = System.currentTimeMillis();
		// }
		if (rotationPhi != vector.getPhi()) {
			this.rotate(rotationPhi - vector.getPhi());
		}
		rotationPhi = this.vector.getPhi();
		// synchronized (runTime) {
		// runTime = System.currentTimeMillis() - runTime;
		// }
		// try {
		// Thread.sleep(GameController.globalFrameTime - runTime);
		// } catch (IllegalArgumentException e) {
		// System.out.println("Time Overload");
		// }
		// return System.currentTimeMillis() - runTime;
	}
}
