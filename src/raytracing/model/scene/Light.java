package raytracing.model.scene;

import raytracing.model.Color;
import raytracing.model.basics.Point;
import raytracing.model.basics.Vector;

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
	
	public Vector getNormalizedDirection(Point p) {
		Vector l = getDirection(p);
		l.normalize();
		return l;
	}
	
	public Vector getDirection(Point p) {
		double x = position.getX() - p.getX();
		double y = position.getY() - p.getY();
		double z = position.getZ() - p.getZ();
		Vector l = new Vector(x, y, z);
		return l;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
}
