package Input;

import java.awt.event.*;

public abstract class InputListener extends Thread implements
		java.awt.event.KeyListener {
	public long timeSlice = 50; // Millisekunden, wie oft soll die Bewegung
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
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			left = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			right = true;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			up = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			down = true;
	}

	public void keyReleased(java.awt.event.KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			left = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			right = false;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			up = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			down = false;
	}

	/*
	 * Ruft alle timeSlice Millisekunden die abstrakte Methode doMovement auf
	 */
	@Override
	public void run() {
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
	 * Halte den Thread bei nächster Gelgenheit an
	 */
	public void end() {
		stopFlag = true;
	}

	public abstract void doMovement(boolean left, boolean right, boolean up, boolean down);
}
