package raytracing.model;

import raytracing.model.basics.Point;
import raytracing.model.basics.Vector;

public class Ray {

	private Point start;
	private Vector direction;
	
	public Ray(Point start, Vector direction) {
		this.start = start;
		this.direction = direction;
	}

	public Point getStart() {
		return start;
	}

	public Vector getDirection() {
		return direction;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public void setDirection(Vector direction) {
		this.direction = direction;
	}
	
	public Point getPointAt(double t) {
		double x = start.getX() + t*direction.getX();
		double y = start.getY() + t*direction.getY();
		double z = start.getZ() + t*direction.getZ();
		return new Point(x, y, z);
	}
}
