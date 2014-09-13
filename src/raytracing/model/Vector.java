package raytracing.model;

public class Vector  extends Coordinates{

	public Vector(int x, int y, int z) {
		super(x, y, z);
		normalize();
		w = 0;
	}
	
	public void normalize() {
		double scale = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
		x /= scale;
		y /= scale;
		z /= scale;
	}
}
