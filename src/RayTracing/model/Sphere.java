package raytracing.model;

public class Sphere extends Object {

	private int centerX, centerY, radius;
	
	public Sphere(int centerX, int centerY, int radius, double ka, double kd, double ks, Color color) {
		super(ka, kd, ks, color);
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getRadius() {
		return radius;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
