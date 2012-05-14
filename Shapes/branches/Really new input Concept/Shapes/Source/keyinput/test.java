package keyinput;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Input testt = new Input(1, 6);
		while(true){
		System.out.println("Phi: "+testt.getKeyInput().getPhi()+" ammount: "+testt.getKeyInput().getAmount());
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}

}
