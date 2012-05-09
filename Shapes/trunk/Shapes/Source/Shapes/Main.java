package Shapes;

import java.awt.Color;

/**
 * class that manage the program
 * 
 * @author (Markus Krummnacker)
 * @version (0.3)
 */
public class Main {
	public static int windowX = 380/2;
	public static int windowY = 275/2;
	public static Point window = new Point(windowX*2, windowY*2);

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Drawable background = new Rectangle(new Point(0, 0), window.getX(),
				window.getY(), Color.BLACK, true);
		background.draw();
		Sprite test1 = Sprite.ship();
		Sprite test2 = new SpaceShip();
		test1.vector.changeSpeed(10);
		test2.vector.changeSpeed(5);
		long time =25;
		for (;;) {
			for (int i = 0; i < 25; i++) {
				test1.rotate(5).draw();
				Thread.sleep(time);
				test1.update();
				test2.update();
				Thread.sleep(time);
			}
			test1.vector.changeDirection(90);
			test2.vector.changeDirection(90);
			for (int i = 0; i < 25; i++) {
				test1.rotate(5).draw();
				Thread.sleep(time);
				test1.update();
				test2.update();
				Thread.sleep(time);
			}
			test1.vector.changeDirection(90);
			test2.vector.changeDirection(90);
			for (int i = 0; i < 25; i++) {
				test1.rotate(5).draw();
				Thread.sleep(time);
				test1.update();
				test2.update();
				Thread.sleep(time);
			}
			test1.vector.changeDirection(90);
			test2.vector.changeDirection(90);
			for (int i = 0; i < 25; i++) {
				test1.rotate(5).draw();
				Thread.sleep(time);
				test1.update();
				test2.update();
				Thread.sleep(time);
			}
			test1.vector.changeDirection(90);
			test2.vector.changeDirection(90);
		}
	}
}
