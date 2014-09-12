package raytracing.model;

public class Coordinates {

	protected int x;
	protected int y;
	protected int z;
	protected int w;
	
	public Coordinates(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getW() {
		return w;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setW(int w) {
		this.w = w;
	}
}
