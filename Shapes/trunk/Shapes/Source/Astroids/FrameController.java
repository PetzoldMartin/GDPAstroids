package Astroids;

import Input.InputListenerAndController;


public class FrameController extends OldGameController implements Runnable{

	@Override
	public void run() {
		try {
			OldGameController.update();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}}
	
