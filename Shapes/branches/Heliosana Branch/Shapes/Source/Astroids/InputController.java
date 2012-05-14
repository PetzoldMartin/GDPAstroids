package Astroids;

import java.awt.Frame;
import java.awt.event.*;


public class InputController implements KeyListener {
	private Frame inputWindow;
	private double keyAmount=0;
	private double keyPhi=0;
	private GameController gameController;

	public InputController(GameController gameController) {

		this.gameController = gameController;
		inputWindow = new Frame("Tasteninput");
		inputWindow.setSize(300, 300);
		inputWindow.setLocation(100, 100);
		inputWindow.setVisible(true);
		inputWindow.requestFocus();
		inputWindow.addKeyListener(this);
	}

	public void keyTyped(KeyEvent e) {
	}

	// events wenn eine taste Gedrückt wird
	public void keyPressed(KeyEvent e) {
		//
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				| e.getKeyCode() == KeyEvent.VK_A)
			keyPhi = -gameController.getKeyRotationAngel();
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				| e.getKeyCode() == KeyEvent.VK_D)
			keyPhi = +gameController.getKeyRotationAngel();
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			keyAmount = gameController.getKeyAcelleration();
		if (e.getKeyCode() == KeyEvent.VK_DOWN
				| e.getKeyCode() == KeyEvent.VK_S)
			keyAmount = -gameController.getKeyAcelleration();
	}

	// events beim Tastenloslassen
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				| e.getKeyCode() == KeyEvent.VK_A)
			keyPhi = 0;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				| e.getKeyCode() == KeyEvent.VK_D)
			keyPhi = 0;
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			keyAmount = 0;
		if (e.getKeyCode() == KeyEvent.VK_DOWN
				| e.getKeyCode() == KeyEvent.VK_S)
			keyAmount = 0;
	}

	public double getKeyAmount() {
		return keyAmount;
	}

	public double getKeyPhi() {
		return keyPhi;
	}
}