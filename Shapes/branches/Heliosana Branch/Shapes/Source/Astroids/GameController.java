package Astroids;

import java.awt.Color;
import java.util.ArrayList;

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
		itself=new GameController();
	}
	public static GameController itself;
	
	// TODO implement setup
	// TODO make non static!!!
	// [setup]
	public double keyRotationAngel = 3;
	public double keyAcelleration = 0.1;
	public int windowX = 380 ;
	public int windowY = 275 ;
	public int frames = 50;
	public double maxSpeed = 10;
	// [setup/]
	public long globalFrameTime = 1000 / frames;
	public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public SpaceShip spaceShip;
	public FrameController frameController;
	public InputController inputController;
	

	// TODO Entwickeln Sie eine Klasse GameController, die alle Sprites kennt.
	// Diese Klasse verfügt über einen Thread zum Aktualisieren aller
	// Sprite-Grafiken. Dieser Thread fordert alle Sprites auf, sich bei Bedarf
	// zu aktualisieren.

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public GameController() {
		Drawable background = new Rectangle(new Point(0, 0), windowX, windowY,
				Color.BLACK, true);
		background.draw();
		new SpaceShip(this);
//		 new Astroid();
//		 new Astroid();
		inputController = new InputController(this);
		frameController = new FrameController(this);
		frameController.start();
	}
 
	public void update(long frameTime) throws InterruptedException {
		Long runTime = System.nanoTime();
		spaceShip.update();
		for (Sprite sprite : sprites) {
			sprite.update();
		}
		try {
			Thread.sleep(runTime = frameTime - (System.nanoTime() - runTime)
					/ 1000000);
		} catch (IllegalArgumentException e) {
			System.out.println("Time Overload: " + (runTime - frameTime));
		}
		// System.out.println(runTime);
	}

	public int getWindowX() {
		return windowX;
	}

	public int getWindowY() {
		return windowY;
	}

	public double getKeyRotationAngel() {
		return keyRotationAngel;
	}

	public double getKeyAcelleration() {
		return keyAcelleration;
	}

}