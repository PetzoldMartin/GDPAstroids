package Astroids;

import Input.InputController;


public class FrameController extends GameController implements Runnable{

	@Override
	public void run() {
		try {
			GameController.update();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
