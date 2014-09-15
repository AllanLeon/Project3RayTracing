package raytracing.model.basics;

public class Point extends Coordinates {

	public Point(double x, double y, double z) {
		super(x, y, z);
		w = 1;
	}
	
	public void checkW() {
		if (w == 0) {
			w = 1;
		} else if (w != 1) {
			x /= w;
			y /= w;
			z /= w;
			w /= w;
		}
	}
}
