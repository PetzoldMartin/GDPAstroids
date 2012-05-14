package keyinput;

import java.awt.Frame;
import java.awt.event.*;

import Astroids.Vector;

public class Input implements KeyListener {
	private Frame inputWindow;
	private Vector keyInput;
	private double speed, direction;

	public Input(double speed, double direction) {
		inputWindow = new Frame("Tasteninput");
		this.speed = speed;
		this.direction = direction;
		inputWindow.setSize(300, 300);
		inputWindow.setLocation(100, 100);
		inputWindow.setVisible(true);
		inputWindow.requestFocus();
		inputWindow.addKeyListener(this);
		keyInput = new Vector(0, 0);
	}

	public void keyTyped(KeyEvent e) {
	}

	// events wenn eine taste Gedrückt wird
	public void keyPressed(KeyEvent e) {
//		keyInput.setVector(0,0);
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				| e.getKeyCode() == KeyEvent.VK_A)
			keyInput.setVector(keyInput.getAmount(), direction);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				| e.getKeyCode() == KeyEvent.VK_D)
			keyInput.setVector(keyInput.getAmount(), -direction);
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			keyInput.setVector(speed, keyInput.getPhi());
		if (e.getKeyCode() == KeyEvent.VK_DOWN
				| e.getKeyCode() == KeyEvent.VK_S)
			keyInput.setVector(-speed, keyInput.getPhi());
	}
// events beim Tastenloslassen
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				| e.getKeyCode() == KeyEvent.VK_A)
			keyInput.setVector(keyInput.getAmount(), 0);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				| e.getKeyCode() == KeyEvent.VK_D)
			keyInput.setVector(keyInput.getAmount(), 0);
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			keyInput.setVector(0, keyInput.getPhi());
		if (e.getKeyCode() == KeyEvent.VK_DOWN
				| e.getKeyCode() == KeyEvent.VK_S)
			keyInput.setVector(0, keyInput.getPhi());
	}

	// Rückgabe des Vectors mit dem man Arbeiten kann
	public Vector getKeyInput() {
		return keyInput;
	}

}