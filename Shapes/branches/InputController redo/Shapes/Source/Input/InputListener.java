package Input;

import java.awt.event.KeyEvent;

import Astroids.GameController;
import Astroids.Vector;

public class InputListener extends Thread implements java.awt.event.KeyListener {
	// Millisekunden, wie oft soll die Bewegung abgefragt werden
	private static boolean left = false;
	private static boolean right = false;
	private static boolean up = false;
	private static boolean down = false;
	private double keyAcelleration;
	private double keyRotationAngel;
	public InputListener(double keyAcelleration,double keyRotationAngel) {
		this.keyAcelleration =keyAcelleration;
		this.keyRotationAngel = keyRotationAngel;
	}
	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				| e.getKeyCode() == KeyEvent.VK_A)
			left = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				| e.getKeyCode() == KeyEvent.VK_D)
			right = true;
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			up = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN
				| e.getKeyCode() == KeyEvent.VK_S)
			down = true;
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				| e.getKeyCode() == KeyEvent.VK_A)
			left = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				| e.getKeyCode() == KeyEvent.VK_D)
			right = false;
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			up = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN
				| e.getKeyCode() == KeyEvent.VK_S)
			down = false;
	}

	public static void changeByInput(double maxspeed) throws InterruptedException {
		// Long runTime = System.nanoTime(); {
		Vector changeVector = new Vector(new Point(0,0));
		if (left == true) {
			changeVector.changeDirection(-keyRotationAngel);
			System.out.println("left");
		}
		if (right == true) {
			changeVector.changeDirection(keyRotationAngel);
			System.out.println("right");
		}
		if (up == true) {
			changeVector.changeSpeed(keyAcelleration,maxspeed);
			System.out.println("up");
		}
		if (down == true) {
			changeVector.changeSpeed(-keyAcelleration,maxspeed);
			System.out.println("down");
			// }
			// try {
			// Thread.sleep(runTime=GameController.globalFrameTime -
			// (System.nanoTime() - runTime)/1000000);
			// } catch (IllegalArgumentException e) {
			// System.out.println("Time Overload: " +
			// (runTime-GameController.globalFrameTime));
			// }
			// System.out.println(runTime);
		}
	}
}
