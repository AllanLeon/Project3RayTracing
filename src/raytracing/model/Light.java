package raytracing.model;

public class Light {

	private Color color;
	private Point position;
	
	public Light(Color color, Point position) {
		this.color = color;
		this.position = position;
	}

	public Color getColor() {
		return color;
	}

	public Point getPosition() {
		return position;
	}
	
	public Vector getDirection(Point p) {
		double x = p.getX() - position.getX();
		double y = p.getY() - position.getY();
		double z = p.getZ() - position.getZ();
		Vector l = new Vector(x, y, z);
		l.normalize();
		return l;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
}
