package raytracing.model;

public class Point extends Coordinates {

	public Point(int x, int y, int z) {
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
