package Input;

/**
 * class to control the inputs
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */
public class InputKeyController extends javax.swing.JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private InputListener InputListener;

	// TODO Spaceship Singled in Gamecontroller

	public InputKeyController() {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(200, 200);

		InputListener = new InputListener();
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
		final InputKeyController AstroidsInput = new InputKeyController();
		AstroidsInput.setVisible(true);

	}

}
