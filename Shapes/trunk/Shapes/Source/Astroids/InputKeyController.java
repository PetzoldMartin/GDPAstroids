package Astroids;

import java.awt.Color;

import Input.InputListener;
import Shapes.*;

/**
 * class to control the inputs
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */

public class InputKeyController extends GameController {
	private static final long serialVersionUID = 1L;
	private InputListener InputListener;
	static Sprite test1 = new SpaceShip();

	public InputKeyController() {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(40, 40);
		InputListener = new InputListener() {

			@Override
			public void doMovement(boolean left, boolean right, boolean up,
					boolean down) {
				{

					if (left == true) {
						test1.vector.changeDirection(-6);
						System.out.println("left");
						

					}
					if (right == true) {
						test1.vector.changeDirection(6);
						System.out.println("right");
						

					}
					if (up == true) {
						test1.vector.changeSpeed(0.1);
						System.out.println("up");
						

					}
					if (down == true) {
						test1.vector.changeSpeed(-0.1);
						System.out.println("down");
						

					}

					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							test1.update();
							try {
								Thread.sleep(GameController.globalFrameTime);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});
				}

			}

		};
		InputListener.setPriority(Thread.NORM_PRIORITY);
	}

	@Override
	public void setVisible(boolean value) {
		if (value == true) {
			this.addKeyListener(InputListener);
			InputListener.start();
		} else {
			InputListener.end();
			this.removeKeyListener(InputListener);
		}
		super.setVisible(value);
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Drawable background = new Rectangle(new Point(0, 0), window.getX(),
				window.getY(), Color.BLACK, true);
		background.draw();
		final InputKeyController AstroidsInput = new InputKeyController();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AstroidsInput.setVisible(true);
				}
		});
		
		
	}

}
