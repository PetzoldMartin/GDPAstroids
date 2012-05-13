package Input;

import java.awt.event.*;

import Astroids.GameController;

public abstract class InputListener extends Thread implements
		java.awt.event.KeyListener {
	public long timeSlice = GameController.globalFrameTime; // Millisekunden, wie oft soll die Bewegung
	// abgefragt werden
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private boolean stopFlag = false;

	public void keyTyped(java.awt.event.KeyEvent e) {
		// do nothing
	}

	public void keyPressed(java.awt.event.KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT | e.getKeyCode() == KeyEvent.VK_A)
			left = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT | e.getKeyCode() == KeyEvent.VK_D)
			right = true;
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			up = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN | e.getKeyCode() == KeyEvent.VK_S)
			down = true;
	}

	public void keyReleased(java.awt.event.KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT | e.getKeyCode() == KeyEvent.VK_A)
			left = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT | e.getKeyCode() == KeyEvent.VK_D)
			right = false;
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			up = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN | e.getKeyCode() == KeyEvent.VK_S)
			down = false;
	}

	/*
	 * Ruft alle timeSlice Millisekunden die abstrakte Methode doMovement auf
	 */
	@Override
	public void run() {
		//TODO Synchronise with Framecontroller
		stopFlag = false;
		long lastRunTime = System.currentTimeMillis();
		while (!stopFlag) {
			long timeDif = System.currentTimeMillis() - lastRunTime;
			try {
				sleep(timeSlice - timeDif);
			} catch (InterruptedException ex) {
			}
			this.doMovement(left, right, up, down);
			lastRunTime = System.currentTimeMillis();
		}
	}

	/*
	 * Halte den Thread bei n�chster Gelgenheit an
	 */
	public void end() {
		stopFlag = true;
	}

	public abstract void doMovement(boolean left, boolean right, boolean up, boolean down);
}
