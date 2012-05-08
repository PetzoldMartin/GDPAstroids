package Shapes;

/**
 * @author heliosana
 * 
 */
public class Vector extends Point {
	private double amount;
	private double phi;

	public Vector() {
		super(0, 0);
		this.amount = 0;
		this.phi = 0;
	}

	/**
	 * @param amount
	 * @param phi
	 */
	public Vector(double amount, double phi) {
		super(amount * Math.cos(phi), amount * Math.sin(phi));
		this.amount = amount;
		this.phi = phi;
	}

	/**
	 * @param vector
	 */
	public Vector(Point vector) {
		super(vector.getX(), vector.getY());
		this.amount = Math.sqrt(Math.pow(this.getX(), 2.0)
				+ Math.pow(this.getY(), 2.0));
		this.phi = Math.atan(vector.getY() / vector.getX());
	}

	/**
	 * @param vector
	 */
	private void setVector(Point vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.amount = Math.sqrt(Math.pow(this.getX(), 2.0)
				+ Math.pow(this.getY(), 2.0));
		this.phi = Math.atan(vector.getY() / vector.getX());
	}

	/**
	 * @param amount
	 * @param phi
	 */
	private void setVector(double amount, double phi) {
		this.amount = amount;
		this.phi = phi;
		this.x = amount * Math.cos(phi);
		this.y = amount * Math.sin(phi);
	}

	/**
	 * @param amount
	 * @param phi
	 * @return
	 */
	public Vector changeVector(double amount, double phi) {
		setVector(this.amount + amount, (this.phi + phi) % 360);
		return this;
	}

	/**
	 * @param vector
	 * @return
	 */
	public Vector changeVector(Point vector) {
		setVector(this.move(vector));
		return this;
	}

	/**
	 * @param phi
	 * @return
	 */
	public Vector changeDirection(double phi) {
		changeVector(0, phi);
		return this;
	}

	/**
	 * @param amount
	 * @return
	 */
	public Vector changeSpeed(double amount) {
		changeVector(amount, 0);
		return this;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Vector) {
			Point otherVector = (Vector) obj;
			if (this.getX() == (otherVector.getX())
					& this.getY() == (otherVector.getY())) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public double getAmount() {
		return amount;
	}

	public double getPhi() {
		return phi;
	}
}
