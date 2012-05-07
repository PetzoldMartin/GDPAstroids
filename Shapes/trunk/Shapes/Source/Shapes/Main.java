package Shapes;

import java.awt.Color;

/**
 * class that manage the program
 * 
 * @author (Markus Krummnacker)
 * @version (0.3)
 */
public class Main {
	public static int windowX = 380;
	public static int WindowY = 275;
	public static Point window = new Point(windowX, WindowY);

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Drawable background = new Rectangle(new Point(window.getX() / 2,
				window.getY() / 2), window.getX(), window.getY(), Color.BLACK,
				true);
		background.draw();
		Figure test = Figure.ship();
		Drawable show = new Circle(1, (test.getMiddle()));
		for (;;) {
			test.rotate(5).draw();
			Thread.sleep(50);
			test.move(new Point(0, 10)).draw();
			Thread.sleep(50);
		}
	}
}
