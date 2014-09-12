package raytracing.model;

public class Color {

	private double r, g, b;
	
	public Color(double r, double g, double b) {
		this.r = r;
		this.g = g;
		this.b = b;
		checkBounds();
	}
	
	public double getR() {
		return r;
	}

	public double getG() {
		return g;
	}

	public double getB() {
		return b;
	}

	public void setR(double r) {
		this.r = r;
	}

	public void setG(double g) {
		this.g = g;
	}

	public void setB(double b) {
		this.b = b;
	}

	public void checkBounds() {
		if (r < 0) {
			r = 0;
		} else if (r > 1) {
			r = 1;
		}
		if (g < 0) {
			g = 0;
		} else if (g > 1) {
			g = 1;
		}
		if (b < 0) {
			b = 0;
		} else if (b > 1) {
			b = 1;
		}
	}
}
