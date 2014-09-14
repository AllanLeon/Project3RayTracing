package raytracing.model;

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
}
