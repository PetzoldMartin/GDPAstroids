package Astroids;

public class FrameController extends Thread implements Runnable {
	private double maxSpeed;
	private long frameTime;
	private GameController gameController;

	public FrameController(GameController gameController) {
		this.gameController = gameController;
		this.maxSpeed = gameController.maxSpeed;
		this.frameTime = gameController.globalFrameTime;
	}
	@Override
	public void run() {
		// TODO Sprites.updateAll()
		// TODO InputKeyController.refresh()
		while (true) {
			try {
				gameController.spaceShip.changeVector(gameController.inputController.getKeyAmount(),gameController.inputController.getKeyPhi(),maxSpeed);
				gameController.update(frameTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
