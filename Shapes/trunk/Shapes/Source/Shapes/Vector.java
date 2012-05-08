package Shapes;

public class Vector {
	private Point vector;
	private double amount;
	private double phi;

	public Vector() {
		vector = new Point(0, 0);
		//super(0, 0);
		amount = 0;
		phi = 0;
	}

	public Vector(double amount, double phi) {
		setVector(amount, phi);
	}

	public Vector(Point vector) {
		setVector(vector);
	}

	private void setVector(Point vector) {
		this.vector = vector;
		amount = Math.sqrt(Math.pow(this.vector.getX(), 2.0)
				+ Math.pow(this.vector.getY(), 2.0));
		phi = Math.atan(vector.getY() / vector.getX());
	}

	private void setVector(double amount, double phi) {
		this.amount = amount;
		this.phi = phi;
		this.vector = new Point(amount * Math.cos(phi), amount * Math.sin(phi));
	}

	public Point changeVector(double amount, double phi) {
		setVector(this.amount + amount, this.phi + phi);
		return this.vector;
	}

	public Point changeVector(Point vector) {
		setVector(this.vector.move(vector));
		return this.vector;
	}

	public Point getVector() {
		return vector;
	}

	public double getAmount() {
		return amount;
	}

	public double getPhi() {
		return phi;
	}

}
