package Input;

import java.awt.Color;

import Astroids.OldGameController;
import Astroids.SpaceShip;
import Astroids.Sprite;
import Shapes.*;

/**
 * class to control the inputs
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */
public class OldInputKeyController extends javax.swing.JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private OldInputListener InputListener;

	// TODO Spaceship Singled in Gamecontroller

	public OldInputKeyController() {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(200, 200);
		InputListener = new OldInputListener() {

			@Override
			public void doMovement(boolean left, boolean right, boolean up,
					boolean down) {
				{

					if (left == true) {
						OldGameController.spaceShip.vector
								.changeDirection(-OldGameController.keyRotationAngel);
						System.out.println("left");

					}
					if (right == true) {
						OldGameController.spaceShip.vector
								.changeDirection(OldGameController.keyRotationAngel);
						System.out.println("right");

					}
					if (up == true) {
						OldGameController.spaceShip.vector
								.changeSpeed(OldGameController.keyAcelleration);
						System.out.println("up");

					}
					if (down == true) {
						OldGameController.spaceShip.vector
								.changeSpeed(-OldGameController.keyAcelleration);
						System.out.println("down");

					}


				}

			}

		};
		InputListener.setPriority(Thread.NORM_PRIORITY);
	}

	@Override
	public void setVisible(boolean value) {
		
		this.addKeyListener(InputListener);
		InputListener.start();

		super.setVisible(value);
	}

	

	@Override
	public void run() {
		// TODO check keys and change direktion and speed of SpaceShip
		// TODO implemt in FrameController
		final OldInputKeyController AstroidsInput = new OldInputKeyController();
		AstroidsInput.setVisible(true);
		while (true) {
			try {
				OldGameController.update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
