package Astroids;

public class FrameController extends Thread implements Runnable {

	@Override
	public void run() {
		// TODO Sprites.updateAll()
		// TODO InputKeyController.refresh()
		for (;;) {
			try {
				GameController.update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
