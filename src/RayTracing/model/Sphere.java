package raytracing.model;

public class Sphere extends Object {

	private Point center;
	private int radius;
	
	public Sphere(Point center, int radius, double ka, double kd, double ks, Color color) {
		super(ka, kd, ks, color);
		this.center = center;
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public int getRadius() {
		return radius;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
