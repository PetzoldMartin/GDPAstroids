package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Input.InputController;
import Shapes.Drawable;
import Shapes.Point;
import Shapes.Rectangle;

/**
 * class that manage the program
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */
public class GameController extends Thread implements Runnable {

	public static void main(String[] args) {
		new GameController().start();
	}
	// [setup]
	private double keyRotationAngel = 6;
	private double keyAcelleration = 0.2;
	// Astro
	private int astroCount=0;
	private int astroSize=20;
	private int astroEdge=24;
	//Window
	private int windowX = 389 - astroSize; // max std 389
	private int windowY = 278 - astroSize; // max std 278
	private int frames = 30;
	//Ship
	private double maxSpeed = 10;
	// [setup/]
	private long globalFrameTime = 1000 / frames; // time of a Frame in millis
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private SpaceShip spaceShip;
	private FrameController frameController;
	private InputController inputController;
	Astroid test;

	// TODO Entwickeln Sie eine Klasse GameController, die alle Sprites kennt.
	// Diese Klasse verfügt über einen Thread zum Aktualisieren aller
	// Sprite-Grafiken. Dieser Thread fordert alle Sprites auf, sich bei Bedarf
	// zu aktualisieren.

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public void run() {
		System.out.println("GameController started:\t" + this.getId());
		new SpaceShip(this);
		Drawable backgroundFrame = new Rectangle(new Point(0, 0), windowX + astroSize,
				windowY + astroSize, Color.WHITE, true);
		Drawable background = new Rectangle(new Point(0, 0), windowX, windowY,
				Color.BLACK, true);
		backgroundFrame.draw();
		background.draw();
		for (int i = 0; i < astroCount; i++) {
			new Astroid(astroEdge,astroSize);
		}
		test = new Astroid(astroEdge,astroSize);
		inputController = new InputController(this);
		frameController = new FrameController(this);
		// inputController.start();
		frameController.start();

	}

	public void makeTest() {
		test.remove();
	}

	public void spaceKey() {
		spaceShip.fire();		
	}

	public void update() {
		getSpaceShip().update();
//		 System.out.println(getSpaceShip().getMiddlePoint().getX() + "\t" + getSpaceShip().getMiddlePoint().getY());
		for (Sprite sprite : sprites) {
//			System.out.println(sprite.centerPoint.getX() + "\t" + sprite.centerPoint.getY());
			sprite.update();
		}
	}

	public void pause() {
		if (this.frameController.getPause()==false) {
			this.frameController.setPause(true);			
		}
		else this.frameController.setPause(false);
	}

	public void setWindowActivated(boolean windowActivated) {
		frameController.setWindowActivated(windowActivated);
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

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public long getGlobalFrameTime() {
		return globalFrameTime;
	}

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public void addSprites(Sprite sprite) {
		this.sprites.add(sprite);
	}
	public void removeSprites(Sprite sprite) {
		this.sprites.remove(sprite);
	}
	public SpaceShip getSpaceShip() {
		return spaceShip;
	}

	public void setSpaceShip(SpaceShip spaceShip) {
		this.spaceShip = spaceShip;
	}

	public InputController getInputController() {
		return inputController;
	}

	public void setInputController(InputController inputController) {
		this.inputController = inputController;
	}

	public void setTestflag(boolean testFlag) {
		frameController.testFlag = testFlag;
	}
}