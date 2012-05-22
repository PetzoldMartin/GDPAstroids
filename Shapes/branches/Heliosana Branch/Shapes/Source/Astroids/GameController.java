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
 * @author (Martin Petzold , Markus Krummnacker)
 * @version (0.3)
 */
public class GameController extends Thread implements Runnable {
	// TODO commenting
	public static void main(String[] args) {
		new GameController();
	}

	// [setup]
	// Window
	private int windowX = 400; // max std 389
	private int windowY = 300; // max std 278
	private int frames = 30;
	// Ship
	private double keyAcelleration = 0.2;
	private double keyRotationAngel = 6;
	private double maxSpeed = 10;
	private int framesPerShot = 10; // how many frames between the shots!
	// Astro
	private int astroCount = 10;
	private int astroSize = 30;
	private int astroEdge = 24;
	// [setup/]
	private int gameScreenX = windowX - astroSize;
	private int gameScreenY = windowY - astroSize;
	private long frameTime = 1000 / frames; // time of a Frame in millis
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> removals = new ArrayList<Sprite>();
	private ArrayList<Sprite> adds = new ArrayList<Sprite>();
	private SpaceShip spaceShip;
	private InputController inputController;
	// flags
	private boolean pause = false;
	private boolean windowActivated = true;
	public boolean testFlag;
	// testing
	private Sprite test;

	/**
	 * @param args
	 * @throws InterruptedException
	 */

	public GameController() {
		Sprite.setGameController(this);
		System.out.println("GameController initialisiert:\t" + this.getId());
		Drawable backgroundFrame = new Rectangle(new Point(0, 0), windowX,
				windowY, Color.WHITE, true);
		Drawable background = new Rectangle(new Point(0, 0), gameScreenX,
				gameScreenY, Color.BLACK, true);
		backgroundFrame.draw();
		background.draw();
		inputController = new InputController(this);
		inputController.start();
		checkObjects();
		this.start();
	}

	public void run() {
		System.out.println("GameController started:\t" + this.getId());
		for (;;) {
			Long runTime = System.nanoTime();
			if (!pause && windowActivated) {
				spaceShip.changeVector(inputController.getKeyAmount(),
						inputController.getKeyPhi(), maxSpeed);
				update();
				inputController.Interfacerefresh();
				if (testFlag == true) {
					makeTest();
				}
				// System.out.println((double)(System.nanoTime() - runTime)
				// /1000000);
			}
			try {
				Thread.sleep(runTime = frameTime
						- (System.nanoTime() - runTime) / 1000000);
			} catch (IllegalArgumentException e) {
				System.out.println("FrameTime overrun:\t " + (-runTime));
			} catch (InterruptedException e) {
				System.out.println("Frame interuppted");
				e.printStackTrace();
			}
		}
	}

	public void checkObjects() {
		// TODO implemts the controlling of all object:
		// count of Astroids
		// one SpaceShip
		// ...
		for (int i = 0; i < astroCount; i++) {
			new Astroid(astroEdge, astroSize);
		}
		new SpaceShip();
	}

	public void makeTest() {
		new Astroid(astroEdge, astroSize);
	}

	public void spaceKey() {
		spaceShip.fire(framesPerShot);
	}

	public void update() {
		// remove
		for (Sprite toRemove : removals) {
			toRemove.remove();
		}
		removals.clear();
		// add
		for (Sprite toAdd : adds) {
			this.sprites.add(toAdd);
		}
		adds.clear();
		// System.out.println(getSpaceShip().getMiddlePoint().getX() + "\t" +
		// getSpaceShip().getMiddlePoint().getY());
		// update
		for (Sprite sprite : sprites) {
			// System.out.println(sprite.centerPoint.getX() + "\t" +
			// sprite.centerPoint.getY());
			sprite.update();
		}
		for (Sprite spriteA : sprites) {
			for (Sprite spriteB : sprites) {
				if (spriteA != spriteB)
				spriteA.radiusCollison(spriteB);
			}
		}
	}

	public void pause() {
		if (this.pause == false) {
			this.pause = true;
		} else
			this.pause = false;
	}

	public void addSprites(Sprite sprite) {
		this.adds.add(sprite);
	}

	public void removeSprites(Sprite sprite) {
		this.removals.add(sprite);
	}

	public void deleteSprite(Sprite sprite) {
		this.sprites.remove(sprite);
	}

	public void setInputController(InputController inputController) {
		this.inputController = inputController;
	}

	public void setWindowActivated(boolean windowActivated) {
		this.windowActivated = windowActivated;
	}

	public void setSpaceShip(SpaceShip spaceShip) {
		this.spaceShip = spaceShip;
	}

	public void setTestflag(boolean testFlag) {
		this.testFlag = testFlag;
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
		return frameTime;
	}

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public SpaceShip getSpaceShip() {
		return spaceShip;
	}

	public InputController getInputController() {
		return inputController;
	}

	public int getAstroSize() {
		return astroSize;
	}

	public int getGameScreenX() {
		return gameScreenX;
	}

	public int getGameScreenY() {
		return gameScreenY;
	}
}