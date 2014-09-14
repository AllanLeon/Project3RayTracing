package raytracing.model.scene;

import raytracing.model.Color;
import raytracing.model.Ray;
import raytracing.model.basics.Point;
import raytracing.model.basics.Vector;

public abstract class Object {

	//n: specular reflection exponent
	private int n;
	private double kd, ks;
	private Color color;
	
	public Object(Color color, double kd, double ks, int n) {
		this.kd = kd;
		this.ks = ks;
		this.color = color;
		this.n = n;
	}

	public double getKd() {
		return kd;
	}

	public double getKs() {
		return ks;
	}

	public Color getColor() {
		return color;
	}
	
	public int getN() {
		return n;
	}

	public void setKd(double kd) {
		this.kd = kd;
	}

	public void setKs(double ks) {
		this.ks = ks;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public abstract Vector getNormal(Point p);
	
	public abstract double checkIntersection(Ray ray);
}
