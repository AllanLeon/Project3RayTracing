package raytracing.model;

public class Sphere extends Object {

	private Point center;
	private double radius;
	
	public Sphere(Point center, double radius, double ka, double kd, double ks, Color color) {
		super(ka, kd, ks, color);
		this.center = center;
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
