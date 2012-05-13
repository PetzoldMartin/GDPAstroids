package Input;

import java.awt.event.KeyEvent;

import Astroids.GameController;

public class InputListener extends Thread implements java.awt.event.KeyListener {
	public long timeSlice = GameController.globalFrameTime; // Millisekunden,
															// wie oft soll die
															// Bewegung
	// abgefragt werden
	private static boolean left = false;
	private static boolean right = false;
	private static boolean up = false;
	private static boolean down = false;

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

	public static void changeByInput() throws InterruptedException {
		// Long runTime = System.nanoTime(); {
		if (left == true) {
			GameController.spaceShip.vector
					.changeDirection(-GameController.keyRotationAngel);
			System.out.println("left");
		}
		if (right == true) {
			GameController.spaceShip.vector
					.changeDirection(GameController.keyRotationAngel);
			System.out.println("right");
		}
		if (up == true) {
			GameController.spaceShip.vector
					.changeSpeed(GameController.keyAcelleration);
			System.out.println("up");
		}
		if (down == true) {
			GameController.spaceShip.vector
					.changeSpeed(-GameController.keyAcelleration);
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
