package Astroids;

import Input.InputListener;

public class FrameController extends Thread implements Runnable {

	@Override
	public void run() {
		// TODO Sprites.updateAll()
		// TODO InputKeyController.refresh()
		while (true) {
			try {
				InputListener.changeByInput();
				GameController.update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
