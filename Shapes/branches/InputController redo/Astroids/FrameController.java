package Astroids;

import Input.InputKeyController;
import Input.InputListener;

public class FrameController extends Thread implements Runnable {
	private double maxSpeed;
	private long frameTime;
	private GameController gameController;
	private Thread inputKeyController;

	public FrameController(long globalFrameTime, double maxSpeed,GameController gameController, Thread inputKeyController) {
		this.maxSpeed=maxSpeed;
		this.frameTime=globalFrameTime;
		this.gameController= gameController;
		
		}
	
	@Override
	public void run() {
		// TODO Sprites.updateAll()
		// TODO InputKeyController.refresh()
		while (true) {
			try {
				InputListener.changeByInput(maxSpeed);
				gameController.update(frameTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
