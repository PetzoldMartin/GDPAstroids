package Astroids;

public class FrameController extends Thread {
	private double maxSpeed;
	private long frameTime;
	private GameController gameController;
	private boolean pause;

	public FrameController(GameController gameController) {
		this.gameController = gameController;
		this.maxSpeed = gameController.getMaxSpeed();
		this.frameTime = gameController.getGlobalFrameTime();
	}

	@Override
	public void run() {
		System.out.println("FrameController started:\t" + this.getId());
		for (;;) {
			Long runTime = System.nanoTime();
			if (pause != true) {
			gameController.getSpaceShip().changeVector(
					gameController.getInputController().getKeyAmount(),
					gameController.getInputController().getKeyPhi(), maxSpeed);
			gameController.update();
			gameController.getInputController().Interfacerefresh();
			}
			try {
				Thread.sleep(runTime = frameTime
						- (System.nanoTime() - runTime) / 1000000);
			} catch (IllegalArgumentException e) {
				System.out.println("FrameTime overrun:\t "
						+ (-runTime));
			} catch (InterruptedException e) {
				System.out.println("Frame interuppted");
				e.printStackTrace();
			}
			// System.out.println(runTime);
		}
	}

	public void setPause(boolean pause) {
		this.pause=pause;
	}

	public boolean getPause() {
		return pause;
	}
}
