package Astroids;

import java.awt.Color;

import Input.InputListener;
import Shapes.*;

/**
 * class to test controller funktions
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */

public class TestController extends GameController {
	private static final long serialVersionUID = 1L;
	private InputListener InputListener;
	static Sprite test1 = new SpaceShip();

	public TestController() {
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
						test1.update();

					}
					if (right == true) {
						test1.vector.changeDirection(6);
						System.out.println("right");
						test1.update();

					}
					if (up == true) {
						test1.vector.changeSpeed(0.1);
						System.out.println("up");
						test1.update();

					}
					if (down == true) {
						test1.vector.changeSpeed(-0.1);
						System.out.println("down");
						test1.update();

					}

					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {

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


		final TestController AstroidsInput = new TestController();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AstroidsInput.setVisible(true);
					

			}
		});
		
		
	}

}
