package Shapes;

import java.awt.Color;
import Shapes.Sprite.*;

/**
 * class that manage the program
 * 
 * @author (Martin Petzold)
 * @version (0.3)
 */
public class GameController {
	public static int windowX = 380;
	public static int windowY = 275;
	public static Point window = new Point(windowX, windowY);

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Drawable background = new Rectangle(new Point(0, 0), window.getX(),
				window.getY(), Color.BLACK, true);
		background.draw();
		SpaceShip test = new SpaceShip();
		for (;;) {
			for (int i = 0; i < 25; i++) {
				test.rotate(5).draw();
				Thread.sleep(10);
				test.move(new Point(0, i)).draw();
				Thread.sleep(10);
			}
			for (int i = 0; i < 25; i++) {
				test.rotate(5).draw();
				Thread.sleep(10);
				test.move(new Point(i, 0)).draw();
				Thread.sleep(10);
			}
			for (int i = 0; i < 25; i++) {
				test.rotate(5).draw();
				Thread.sleep(10);
				test.move(new Point(0, -i)).draw();
				Thread.sleep(10);
			}
			for (int i = 0; i < 25; i++) {
				test.rotate(5).draw();
				Thread.sleep(10);
				test.move(new Point(-i, 0)).draw();
				Thread.sleep(10);
			}
		}
	}
}
