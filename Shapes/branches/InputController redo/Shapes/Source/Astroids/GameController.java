package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Input.InputKeyController;
import Shapes.Drawable;
import Shapes.Point;
import Shapes.Rectangle;

/**
 * class that manage the program
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */
public class GameController extends Thread {
	public static void main(String[] args) {
		new GameController();
	}

	// TODO implement setup
	// TODO make non static!!!
	// [setup]
	public  double keyRotationAngel = 3;
	public  double keyAcelleration = 0.1;
	public static int windowX = 380 / 2;
	public static int windowY = 275 / 2;
	public int frames = 50;
	public double maxSpeed = 10;
	// [setup/]
	public long globalFrameTime = 1000 / frames;
	public static Point window = new Point(windowX * 2, windowY * 2);
	public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public static SpaceShip spaceShip;

	// TODO Entwickeln Sie eine Klasse GameController, die alle Sprites kennt.
	// Diese Klasse verfügt über einen Thread zum Aktualisieren aller
	// Sprite-Grafiken. Dieser Thread fordert alle Sprites auf, sich bei Bedarf
	// zu aktualisieren.

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public GameController() {
		Drawable background = new Rectangle(new Point(0, 0), window.getX(),
				window.getY(), Color.BLACK, true);
		background.draw();
		new SpaceShip();
//		new Astroid();
//		new Astroid();
		Thread inputKeyController = new Thread(new InputKeyController(keyAcelleration,keyRotationAngel));
		Thread frameController = new FrameController(globalFrameTime,maxSpeed,this,inputKeyController);
		frameController.start();
		inputKeyController.start();
	}

	public void update(long frameTime) throws InterruptedException {
		Long runTime = System.nanoTime();
		spaceShip.update();
		for (Sprite sprite : sprites) {
			sprite.update();
		}
		try {
			Thread.sleep(runTime = frameTime
					- (System.nanoTime() - runTime) / 1000000);
		} catch (IllegalArgumentException e) {
			System.out.println("Time Overload: " + (runTime - frameTime));
		}
		// System.out.println(runTime);
	}

}