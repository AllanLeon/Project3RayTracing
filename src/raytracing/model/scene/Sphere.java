package raytracing.model.scene;

import raytracing.intersector.Intersector;
import raytracing.model.Color;
import raytracing.model.Ray;
import raytracing.model.basics.Point;
import raytracing.model.basics.Vector;

public class Sphere extends Object {

	private Point center;
	private double radius;
	
	public Sphere(Point center, double radius, Color color, double kd, double ks, int n, boolean ismirror) {
		super(color, kd, ks, n, ismirror);
		this.center = center;
		this.radius = radius;
	}
	
	public Sphere(Point center, double radius, Color color, double kd, double ks, int n) {
		super(color, kd, ks, n);
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
	
	@Override
	public Vector getNormal(Point p) {
		double x = p.getX() - center.getX();
		double y = p.getY() - center.getY();
		double z = p.getZ() - center.getZ();
		Vector r = new Vector(x, y, z);
		r.normalize();
		return r;
	}

	@Override
	public double checkIntersection(Ray ray) {
		return Intersector.raySphereIntersection(ray, this);
	}
}
