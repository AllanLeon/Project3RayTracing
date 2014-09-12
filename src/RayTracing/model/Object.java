package raytracing.model;

public class Object {

	private double ka, kd, ks;
	private Color color;
	
	public Object(double ka, double kd, double ks, Color color) {
		this.ka = ka;
		this.kd = kd;
		this.ks = ks;
		this.color = color;
	}

	public double getKa() {
		return ka;
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

	public void setKa(double ka) {
		this.ka = ka;
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
}
